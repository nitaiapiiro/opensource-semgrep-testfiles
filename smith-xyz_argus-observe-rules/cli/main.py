#!/usr/bin/env python3
"""
Argus Observe Rules CLI - For rule management and testing
"""

import json
import logging
import re
import subprocess
import tempfile
from pathlib import Path
from typing import List, Optional

import typer
import yaml

app = typer.Typer(help="Argus Observe Rules - Security rule management and testing")

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


def find_project_root(start_path: Path = None) -> Path:
    """Find project root by looking for pyproject.toml or .git"""
    if start_path is None:
        start_path = Path.cwd()

    current = start_path.resolve()
    while current != current.parent:
        if (current / "pyproject.toml").exists() or (current / ".git").exists():
            return current
        current = current.parent

    return Path.cwd()


PROJECT_ROOT = find_project_root()
RULES_DIR = PROJECT_ROOT / "rules"
TESTS_DIR = PROJECT_ROOT / "tests"


@app.command()
def run(
    target_dir: str = typer.Argument(..., help="Directory to scan with rules"),
    patterns: List[str] = typer.Argument(
        None, help="Rule patterns to match (e.g., 'crypto', 'md5', 'go-weak-*')"
    ),
    all_rules: bool = typer.Option(False, "--all", help="Run all available rules"),
    verbose: bool = typer.Option(False, "--verbose", "-v", help="Show detailed output"),
):
    """Run security rules against a target directory"""
    target_dir = Path(target_dir).resolve().absolute()

    if not target_dir.exists():
        typer.echo(f"Error: Target directory {target_dir} does not exist")
        return typer.Exit(1)

    if all_rules:
        patterns = None

    rules = load_semgrep_rules(patterns=patterns, verbose=verbose)

    ## write to temporary file to execute for semgrep cli
    with tempfile.NamedTemporaryFile(
        mode="w", prefix="semgrep-", suffix=".yaml", delete=True
    ) as temp_file:
        rule_content = {"rules": rules}
        temp_file.write(yaml.dump(rule_content))
        temp_file.flush()
        temp_file_path = temp_file.name

        semgrep_cli = ["semgrep", "--config", temp_file_path, target_dir]
        subprocess.run(semgrep_cli, check=True)


def run_semgrep_validate(
    config_path: Path, verbose: bool = False
) -> subprocess.CompletedProcess:
    """Run semgrep validate with better error handling"""
    try:
        result = subprocess.run(
            ["semgrep", "--validate", "--config", str(config_path)],
            capture_output=True,
            text=True,
            check=False,
        )

        if result.returncode != 0:
            if verbose:
                typer.echo(f"Config file: {config_path}", err=True)
                typer.echo(f"Return code: {result.returncode}", err=True)
            typer.echo(result.stderr, err=True)
            raise typer.Exit(result.returncode)

        return result
    except FileNotFoundError:
        typer.echo("Error: semgrep not found. Is it installed?", err=True)
        raise typer.Exit(1)
    except Exception as e:
        typer.echo(f"Unexpected error: {e}", err=True)
        if verbose:
            import traceback

            typer.echo(traceback.format_exc(), err=True)
        raise typer.Exit(1)


def count_rules_in_path(path: Path) -> int:
    """Count rules in a file or directory"""
    if path.is_file():
        try:
            with open(path, "r") as f:
                content = yaml.safe_load(f)
                return len(content.get("rules", []))
        except Exception:
            return 0
    elif path.is_dir():
        count = 0
        for rule_file in path.rglob("*.yml"):
            try:
                with open(rule_file, "r") as f:
                    content = yaml.safe_load(f)
                    count += len(content.get("rules", []))
            except Exception:
                continue
        return count
    return 0


@app.command()
def validate(
    rules_path: Optional[str] = typer.Option(
        None, "--rules", "-r", help="Specific rule file or directory to validate"
    ),
    pattern: Optional[str] = typer.Option(
        None, "--pattern", "-p", help="Pattern to match rule filenames"
    ),
    verbose: bool = typer.Option(False, "--verbose", "-v", help="Show detailed output"),
):
    """Validate rule files with semgrep"""
    if rules_path:
        rules_path_obj = Path(rules_path)
        if not rules_path_obj.exists():
            typer.echo(f"Error: Path {rules_path} does not exist", err=True)
            raise typer.Exit(1)
        rule_count = count_rules_in_path(rules_path_obj)
        if verbose:
            typer.echo(f"Validating {rule_count} rule(s) from: {rules_path}")
        run_semgrep_validate(rules_path_obj, verbose=verbose)
        typer.echo(f"✓ Successfully validated {rule_count} rule(s)")
    elif pattern:
        if verbose:
            typer.echo(f"Validating rules matching pattern: {pattern}")
        rules = load_semgrep_rules(patterns=[pattern], verbose=verbose)
        if len(rules) == 0:
            typer.echo(f"No rules found matching pattern: {pattern}", err=True)
            raise typer.Exit(1)
        rule_count = len(rules)
        with tempfile.NamedTemporaryFile(
            mode="w",
            prefix="semgrep-",
            suffix=".yaml",
            delete=True,
        ) as temp_file:
            rule_content = {"rules": rules}
            temp_file.write(yaml.dump(rule_content))
            temp_file.flush()
            temp_file_path = Path(temp_file.name)
            run_semgrep_validate(temp_file_path, verbose=verbose)
        typer.echo(f"✓ Successfully validated {rule_count} rule(s)")
    else:
        if verbose:
            typer.echo("Validating all rules")
        rules = load_semgrep_rules(verbose=verbose)
        rule_count = len(rules)
        with tempfile.NamedTemporaryFile(
            mode="w",
            prefix="semgrep-",
            suffix=".yaml",
            delete=True,
        ) as temp_file:
            rule_content = {"rules": rules}
            temp_file.write(yaml.dump(rule_content))
            temp_file.flush()
            temp_file_path = Path(temp_file.name)
            run_semgrep_validate(temp_file_path, verbose=verbose)
        typer.echo(f"✓ Successfully validated {rule_count} rule(s)")


def _represent_str_literal(dumper, data):
    """Represent multiline strings as literal block scalars (|)"""
    if "\n" in data:
        return dumper.represent_scalar("tag:yaml.org,2002:str", data, style="|")
    return dumper.represent_scalar("tag:yaml.org,2002:str", data)


yaml.add_representer(str, _represent_str_literal)


def _write_config_file(output_path: Path, filename: str, rules: list) -> Path:
    """Write rules to a YAML config file and return the file path"""
    config_file = output_path / filename
    with open(config_file, "w") as f:
        yaml.dump(
            {"rules": rules},
            f,
            sort_keys=False,
            default_flow_style=False,
            allow_unicode=True,
        )
    return config_file


def _discover_languages_and_categories() -> dict[str, list[str]]:
    """Discover available languages and their categories from the rules directory"""
    languages_dir = RULES_DIR / "languages"
    if not languages_dir.exists():
        return {}

    languages_categories = {}
    for lang_dir in sorted(languages_dir.iterdir()):
        if lang_dir.is_dir():
            lang_name = lang_dir.name
            categories = []
            for category_dir in sorted(lang_dir.iterdir()):
                if category_dir.is_dir() and list(category_dir.glob("*.yml")):
                    categories.append(category_dir.name)
            if categories:
                languages_categories[lang_name] = categories

    return languages_categories


@app.command()
def generate_config(
    language: Optional[str] = typer.Option(
        None,
        "--language",
        "-l",
        help='Language to include (e.g., "go", "python"). Use "all" for all languages. Omit for all languages.',
    ),
    category: Optional[str] = typer.Option(
        None,
        "--category",
        "-c",
        help='Category to include (e.g., "crypto", "pqc"). Omit to include all categories.',
    ),
    name: Optional[str] = typer.Option(
        None,
        "--name",
        "-n",
        help="Output filename (e.g., 'my-config.yml'). Auto-generated if not provided.",
    ),
    output_dir: str = typer.Option(
        "configs", "--output-dir", "-o", help="Output directory for config files"
    ),
    validate: bool = typer.Option(
        True, "--validate/--no-validate", help="Validate generated configs with semgrep"
    ),
    verbose: bool = typer.Option(False, "--verbose", "-v", help="Show detailed output"),
) -> None:
    """Generate unified Semgrep config files for external use

    Examples:
        # Generate config for Go crypto rules
        observe generate-config --language go --category crypto --name crypto-go.yml

        # Generate config for all crypto rules across all languages
        observe generate-config --category crypto --name crypto-all.yml

        # Generate config for all rules
        observe generate-config --name all-rules.yml

        # Generate config for Python PQC rules
        observe generate-config --language python --category pqc --name python-pqc.yml
    """
    output_path = Path(output_dir).resolve()
    output_path.mkdir(parents=True, exist_ok=True)

    languages_categories = _discover_languages_and_categories()
    if not languages_categories:
        typer.echo(
            "Error: No languages or categories found in rules directory", err=True
        )
        raise typer.Exit(1)

    if verbose:
        typer.echo(f"Available languages: {list(languages_categories.keys())}")
        for lang, cats in languages_categories.items():
            typer.echo(f"  {lang}: {cats}")

    # Determine which languages to include
    if language and language.lower() != "all":
        if language not in languages_categories:
            typer.echo(
                f"Error: Language '{language}' not found. Available: {list(languages_categories.keys())}",
                err=True,
            )
            raise typer.Exit(1)
        target_languages = [language]
    else:
        target_languages = list(languages_categories.keys())

    # Determine which categories to include
    if category:
        # Verify category exists in at least one target language
        found_category = False
        for lang in target_languages:
            if category in languages_categories.get(lang, []):
                found_category = True
                break
        if not found_category:
            all_categories = set()
            for cats in languages_categories.values():
                all_categories.update(cats)
            typer.echo(
                f"Error: Category '{category}' not found in target languages. Available: {sorted(all_categories)}",
                err=True,
            )
            raise typer.Exit(1)
        target_categories = [category]
    else:
        # Collect all categories from target languages
        target_categories = set()
        for lang in target_languages:
            target_categories.update(languages_categories.get(lang, []))
        target_categories = sorted(target_categories)

    if verbose:
        typer.echo(f"Target languages: {target_languages}")
        typer.echo(f"Target categories: {target_categories}")

    # Build patterns to match rules
    patterns = []
    for lang in target_languages:
        for cat in target_categories:
            if cat in languages_categories.get(lang, []):
                pattern = f"{lang}-{cat}"
                patterns.append(pattern)

    if not patterns:
        typer.echo(
            f"Error: No rules found matching language(s) {target_languages} and category/categories {target_categories}",
            err=True,
        )
        raise typer.Exit(1)

    if verbose:
        typer.echo(f"Loading rules with patterns: {patterns}")

    # Load rules
    rules = load_semgrep_rules(patterns=patterns, verbose=verbose)

    if not rules:
        typer.echo("Warning: No rules found matching the specified criteria", err=True)
        raise typer.Exit(1)

    # Generate output filename
    if name:
        if not name.endswith((".yml", ".yaml")):
            name = f"{name}.yml"
        output_filename = name
    else:
        # Auto-generate filename
        lang_part = target_languages[0] if len(target_languages) == 1 else "all"
        cat_part = target_categories[0] if len(target_categories) == 1 else "all"
        output_filename = f"{lang_part}-{cat_part}.yml"

    # Write config file
    config_file = _write_config_file(output_path, output_filename, rules)
    typer.echo(f"Generated {config_file} with {len(rules)} rules")

    if validate:
        typer.echo("\nValidating generated config file...")
        try:
            run_semgrep_validate(config_file, verbose=verbose)
            typer.echo(f"✓ Validated {config_file.name}")
        except typer.Exit:
            typer.echo(f"✗ Validation failed for {config_file.name}", err=True)
            raise


@app.command()
def test(
    pattern: Optional[str] = typer.Argument(
        None,
        help="Pattern to match rule filenames (e.g., 'crypto', 'md5', 'go-crypto-md5')",
    ),
    all_rules: bool = typer.Option(False, "--all", help="Test all rules"),
    fail_on_missing: bool = typer.Option(
        False, "--fail-on-missing", help="Fail if rules have no test cases"
    ),
    verbose: bool = typer.Option(False, "--verbose", "-v", help="Show detailed output"),
):
    """Run the full test suite against test cases"""
    if all_rules:
        patterns = []
    elif pattern:
        patterns = [pattern]
    else:
        typer.echo("Error: Must specify a pattern or use --all", err=True)
        typer.echo("Example: observe test crypto  or  observe test --all", err=True)
        raise typer.Exit(1)

    rules = load_semgrep_rules(patterns=patterns, verbose=verbose)
    if len(rules) == 0:
        typer.echo(
            "No rules discovered, verify that your patterns match to rule files.",
            err=True,
        )
        raise typer.Exit(1)

    if verbose:
        typer.echo(f"Testing {len(rules)} rules")
        typer.echo(f"Rules: {[r['id'] for r in rules]}")

    with tempfile.NamedTemporaryFile(
        mode="w",
        prefix="semgrep-",
        suffix=".yaml",
        delete=True,
    ) as temp_file:
        rule_content = {"rules": rules}
        temp_file.write(yaml.dump(rule_content))
        temp_file.flush()
        temp_file_path = temp_file.name

        semgrep_cli = [
            "semgrep",
            "--config",
            temp_file_path,
            "--json",
            "--no-git-ignore",
            str(TESTS_DIR),
        ]

        if verbose:
            typer.echo(f"Running semgrep on: {TESTS_DIR}")

        try:
            result = subprocess.run(
                semgrep_cli, capture_output=True, text=True, check=False
            )

            if result.returncode != 0:
                typer.echo(
                    f"Semgrep failed with return code {result.returncode}", err=True
                )
                if verbose:
                    typer.echo(f"Command: {' '.join(semgrep_cli)}", err=True)
                typer.echo(result.stderr, err=True)
                raise typer.Exit(1)

            semgrep_output = json.loads(result.stdout)
            findings = semgrep_output.get("results", [])

            warnings = semgrep_output.get("warnings", [])
            if warnings:
                typer.echo("\nSemgrep Warnings:", err=True)
                for warning in warnings:
                    rule_id = warning.get("rule_id", "unknown")
                    message = warning.get("message", "")
                    level = warning.get("level", "warning")
                    typer.echo(f"  [{level.upper()}] {rule_id}: {message}", err=True)
                typer.echo("")

            if result.stderr and result.stderr.strip():
                stderr_lines = result.stderr.strip().split("\n")
                warning_lines = [
                    line.strip()
                    for line in stderr_lines
                    if "[WARNING]" in line or (line.strip().startswith("WARNING:") or "Skipping unknown field" in line)
                ]
                if warning_lines:
                    typer.echo("\nSemgrep Warnings:", err=True)
                    for line in warning_lines:
                        typer.echo(f"  {line}", err=True)
                    typer.echo("")

            if verbose:
                typer.echo(f"Found {len(findings)} total findings")

            results_by_rule = group_semgrep_results_by_rule_id(findings)

            test_expectations, missing_tests = load_test_expectations(
                rules, fail_on_missing=fail_on_missing, verbose=verbose
            )

            execute_tests(
                results_by_rule,
                test_expectations,
                len(rules),
                missing_tests,
                verbose=verbose,
            )
        except FileNotFoundError:
            typer.echo("Error: semgrep not found. Is it installed?", err=True)
            raise typer.Exit(1)
        except json.JSONDecodeError as e:
            typer.echo(f"Error: Failed to parse semgrep JSON output: {e}", err=True)
            if verbose:
                typer.echo(f"Semgrep stdout: {result.stdout[:500]}", err=True)
            raise typer.Exit(1)
        except Exception as e:
            typer.echo(f"Unexpected error: {e}", err=True)
            if verbose:
                import traceback

                typer.echo(traceback.format_exc(), err=True)
            raise typer.Exit(1)


# Helper to load up rules from the files to a single list to pass to semgrep
def load_semgrep_rules(patterns: List[str] = [], verbose: bool = False) -> list[dict]:
    if not RULES_DIR.exists():
        typer.echo(f"Error: Rules directory not found: {RULES_DIR}", err=True)
        typer.echo(f"Project root: {PROJECT_ROOT}", err=True)
        raise typer.Exit(1)

    rule_files = list(RULES_DIR.rglob("*.yml"))

    if verbose:
        typer.echo(f"Found {len(rule_files)} rule files in {RULES_DIR}")

    if not patterns:
        matches = rule_files
    else:
        matches = [
            f
            for f in rule_files
            if any(re.search(p, f.name, re.IGNORECASE) for p in patterns)
        ]

    if verbose and patterns:
        typer.echo(f"Matched {len(matches)} rule files with patterns: {patterns}")

    # need to group the semgrep rules as one to run the cli just once
    rules = []
    for rule_file in matches:
        try:
            with open(rule_file, "r") as f:
                rule_content = yaml.safe_load(f)

                if "rules" not in rule_content:
                    if verbose:
                        typer.echo(
                            f"Warning: Missing 'rules' in file {rule_file}", err=True
                        )
                    continue

                rules.extend(rule_content.get("rules", []))
                if verbose:
                    rule_ids = [
                        r.get("id", "unknown") for r in rule_content.get("rules", [])
                    ]
                    typer.echo(
                        f"Loaded {len(rule_ids)} rules from {rule_file.name}: {rule_ids}"
                    )
        except Exception as e:
            error_msg = f"Failed to extract rules from {rule_file.name}: {e}"
            logger.error(error_msg)
            if verbose:
                typer.echo(f"Error: {error_msg}", err=True)

    return rules


def group_semgrep_results_by_rule_id(findings):
    results_by_rule = {}
    for finding in findings:
        rule_id = finding["check_id"].split(".")[-1]
        file_path = Path(finding["path"]).name  # Just filename
        line = finding["start"]["line"]
        col = finding["start"].get("col", 1)
        end_line = finding["end"].get("line", line)
        end_col = finding["end"].get("col", col)

        if rule_id not in results_by_rule:
            results_by_rule[rule_id] = []

        result_entry = {
            "file": file_path,
            "full_path": finding["path"],
            "line": line,
            "col": col,
            "end_line": end_line,
            "end_col": end_col,
            "message": finding["extra"]["message"],
        }

        if "lines" in finding.get("extra", {}):
            result_entry["code_snippet"] = finding["extra"]["lines"]

        results_by_rule[rule_id].append(result_entry)
    return results_by_rule


def load_test_expectations(rules, fail_on_missing: bool = False, verbose: bool = False):
    # Only look for test expectations that match the loaded rules
    if not TESTS_DIR.exists():
        typer.echo(f"Error: Tests directory not found: {TESTS_DIR}", err=True)
        typer.echo(f"Project root: {PROJECT_ROOT}", err=True)
        raise typer.Exit(1)

    rule_expectations = {}
    missing_tests = []

    for rule in rules:
        rule_id = rule["id"]
        # Find test directory that matches this rule ID
        expected_files = list(TESTS_DIR.rglob(f"*/{rule_id}/expected.yml"))

        if not expected_files:
            missing_tests.append(rule_id)
            if fail_on_missing:
                typer.echo(f"ERROR: {rule_id}: no test cases found", err=True)
            elif verbose:
                typer.echo(f"WARNING: {rule_id}: no test cases found")
        else:
            for expected_file in expected_files:
                try:
                    with open(expected_file, "r") as f:
                        expected_data = yaml.safe_load(f)
                        # Store both the expectations and the test directory path
                        test_dir = expected_file.parent
                        rule_expectations[rule_id] = {
                            "expectations": expected_data,
                            "test_dir": str(test_dir),
                        }
                        if verbose:
                            typer.echo(
                                f"Loaded test expectations for {rule_id} from {expected_file.relative_to(TESTS_DIR)}"
                            )
                        break  # Only take the first match
                except Exception as e:
                    error_msg = f"Failed to load {expected_file}: {e}"
                    typer.echo(error_msg, err=True)
                    if verbose:
                        import traceback

                        typer.echo(traceback.format_exc(), err=True)
                    continue

    if fail_on_missing and missing_tests:
        typer.echo(
            f"\nError: {len(missing_tests)} rule(s) have no test cases:", err=True
        )
        for rule_id in missing_tests:
            typer.echo(f"  - {rule_id}", err=True)
        raise typer.Exit(1)

    return rule_expectations, missing_tests


def get_relative_test_path(full_path: str, test_dir: str) -> str:
    """Get path relative to test directory for clearer output"""
    try:
        full = Path(full_path).resolve()
        test = Path(test_dir).resolve()
        if full.is_relative_to(test):
            return str(full.relative_to(test))
    except (ValueError, AttributeError):
        pass
    return Path(full_path).name


def format_finding_location(finding: dict, test_dir: str) -> str:
    """Format a finding location for display"""
    relative_path = get_relative_test_path(finding.get("full_path", ""), test_dir)
    line = finding["line"]
    col = finding.get("col", 1)
    return f"  {relative_path}:{line}:{col}"


def execute_tests(
    results_by_rule,
    rule_expectations,
    total_rules_loaded,
    missing_tests,
    verbose: bool = False,
):
    # Validate results
    passed_tests = 0
    failed_tests = 0

    if verbose:
        typer.echo(f"Running test validation for {len(rule_expectations)} rules...\n")
    else:
        typer.echo("Running test validation...\n")

    # Check each rule that has expectations
    for rule_id, rule_data in rule_expectations.items():
        # Filter actual findings to only include those in the rule's test directory
        rule_test_dir = rule_data["test_dir"]
        all_actual_findings = results_by_rule.get(rule_id, [])
        # Use precise path matching to avoid matching subdirectories with similar names
        # e.g., go-crypto-aes should not match go-crypto-aes-ecb-mode
        actual_findings = [
            f
            for f in all_actual_findings
            if f.get("full_path", "").startswith(rule_test_dir + "/")
            and not f.get("full_path", "")
            .replace(rule_test_dir + "/", "")
            .startswith("../")
        ]

        expected = rule_data["expectations"]
        expected_findings = expected.get("findings") or []
        no_findings_files = expected.get("no_findings") or []

        # Validate expected findings
        missing_findings = []
        unexpected_findings = []
        files_with_unexpected_findings = {}

        # Check if we found all expected findings
        for exp_finding in expected_findings:
            exp_file = Path(exp_finding["file"]).name  # Normalize to just filename
            exp_line = exp_finding["line"]

            found_match = False
            for actual in actual_findings:
                actual_file = Path(actual["file"]).name  # Normalize to just filename
                if actual_file == exp_file and actual["line"] == exp_line:
                    found_match = True
                    break

            if not found_match:
                missing_findings.append(exp_finding)

        # Check if we have any unexpected findings
        for actual in actual_findings:
            actual_file = Path(actual["file"]).name  # Normalize to just filename
            actual_line = actual["line"]

            found_expected = False
            for exp_finding in expected_findings:
                exp_file = Path(exp_finding["file"]).name  # Normalize to just filename
                if exp_file == actual_file and exp_finding["line"] == actual_line:
                    found_expected = True
                    break

            if not found_expected:
                unexpected_findings.append(actual)
                if actual_file not in files_with_unexpected_findings:
                    files_with_unexpected_findings[actual_file] = []
                files_with_unexpected_findings[actual_file].append(actual)

        # Check that no findings files have no actual findings
        no_findings_violations = []
        for no_finding_file in no_findings_files:
            no_finding_filename = Path(
                no_finding_file
            ).name  # Normalize to just filename
            has_findings = any(
                Path(f["file"]).name == no_finding_filename for f in actual_findings
            )
            if has_findings:
                no_findings_violations.append(no_finding_file)

        # Report failures with detailed information
        if missing_findings or unexpected_findings or no_findings_violations:
            typer.echo(f"FAIL: {rule_id}")
            typer.echo(
                f"  Test directory: {get_relative_test_path(rule_test_dir, Path.cwd())}"
            )

            # Summary counts at the top
            typer.echo("\n  Summary:")
            typer.echo(f"    Semgrep found: {len(actual_findings)} finding(s)")
            typer.echo(f"    Expected: {len(expected_findings)} finding(s)")
            typer.echo(f"    Missing: {len(missing_findings)} expected finding(s)")
            typer.echo(f"    Unexpected: {len(unexpected_findings)} finding(s)")

            # Show all actual findings Semgrep found
            if actual_findings:
                typer.echo(f"\n  Semgrep found ({len(actual_findings)} finding(s)):")
                # Group by file for better readability
                findings_by_file = {}
                for actual in actual_findings:
                    actual_file = Path(actual["file"]).name
                    if actual_file not in findings_by_file:
                        findings_by_file[actual_file] = []
                    findings_by_file[actual_file].append(actual)

                for file_name in sorted(findings_by_file.keys()):
                    file_findings = sorted(
                        findings_by_file[file_name], key=lambda x: x["line"]
                    )
                    for actual in file_findings:
                        typer.echo(format_finding_location(actual, rule_test_dir))
                        if verbose:
                            typer.echo(f"    Message: {actual['message']}")
            elif expected_findings:
                typer.echo("\n  Semgrep found: 0 finding(s)")
                typer.echo(
                    "    [WARNING] No findings detected - pattern may not be matching correctly"
                )

            # Show expected findings with match status
            if expected_findings:
                typer.echo(f"\n  Expected findings ({len(expected_findings)}):")
                # Create a set of actual finding locations for quick lookup
                actual_locations = {
                    (Path(f["file"]).name, f["line"]) for f in actual_findings
                }

                # Group expected by file
                expected_by_file = {}
                for exp in expected_findings:
                    exp_file = Path(exp["file"]).name
                    if exp_file not in expected_by_file:
                        expected_by_file[exp_file] = []
                    expected_by_file[exp_file].append(exp)

                for file_name in sorted(expected_by_file.keys()):
                    file_expected = sorted(
                        expected_by_file[file_name], key=lambda x: x["line"]
                    )
                    for exp in file_expected:
                        exp_file = Path(exp["file"]).name
                        exp_line = exp["line"]
                        is_found = (exp_file, exp_line) in actual_locations
                        status = "[✓] Found" if is_found else "[X] Missing"
                        typer.echo(f"    {exp_file}:{exp_line} {status}")

            # Show unexpected findings separately
            if unexpected_findings:
                typer.echo(f"\n  Unexpected findings ({len(unexpected_findings)}):")
                for actual in unexpected_findings:
                    typer.echo(format_finding_location(actual, rule_test_dir))
                    typer.echo(f"    Message: {actual['message']}")

            # Show no findings violations
            if no_findings_violations:
                typer.echo("\n  Files that should have no findings but do:")
                for violation_file in no_findings_violations:
                    typer.echo(f"    {violation_file}")
                    file_findings = [
                        f
                        for f in actual_findings
                        if Path(f["file"]).name == Path(violation_file).name
                    ]
                    for finding in file_findings:
                        typer.echo(format_finding_location(finding, rule_test_dir))
            typer.echo("")
            failed_tests += 1
        else:
            typer.echo(f"PASS: {rule_id}")
            passed_tests += 1

    # Report summary
    total_rules_tested = len(rule_expectations)
    rules_without_tests_count = len(missing_tests)

    summary_parts = [
        f"{passed_tests} passed",
        f"{failed_tests} failed",
        f"{total_rules_tested} total rules tested",
    ]
    if rules_without_tests_count > 0:
        summary_parts.append(f"{rules_without_tests_count} rules without tests")

    typer.echo(f"\nTest Summary: {', '.join(summary_parts)}")

    if rules_without_tests_count > 0:
        typer.echo(f"\nRules without tests ({rules_without_tests_count}):")
        for rule_id in sorted(missing_tests):
            typer.echo(f"  - {rule_id}")

    if failed_tests > 0:
        typer.echo(f"{failed_tests} test(s) failed!")
        raise typer.Exit(1)
    else:
        typer.echo("All tests passed!")
        return


if __name__ == "__main__":
    app()
