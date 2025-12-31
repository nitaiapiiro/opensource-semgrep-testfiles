package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
	"testing"

	"github.com/ghodss/yaml"
	"github.com/stretchr/testify/require"
	"github.com/xeipuuv/gojsonschema"
)

// ext -> [semgrep languages]
var extLangs = map[string][]string{
	"c":          {"c", "cpp"},
	"cs":         {"csharp"},
	"go":         {"go"},
	"java":       {"java"},
	"js":         {"javascript"},
	"py":         {"python"},
	"scala":      nil,
	"ts":         {"typescript"},
	"kt":         nil,
	"m":          nil,
	"swift":      nil,
	"php":        nil,
	"rb":         nil,
	"yaml":       {"yaml"},
	"yml":        {"yaml"},
	"properties": {"generic"},
}

func TestRuleFunctions(t *testing.T) {
	files := ListRuleFilesWithTests()
	require.NotEmpty(t, files)

	rules := ListRuleFiles()
	require.NotEmpty(t, rules)
	require.Less(t, len(rules), len(files), "rules < files")
	for _, rule := range rules {
		require.True(t, strings.HasSuffix(rule, ".yml"))
	}

	tests := ListTestFiles()
	require.NotEmpty(t, tests)
	require.Less(t, len(tests), len(files), "tests < files")
	for _, rule := range tests {
		require.True(t, !strings.HasSuffix(rule, ".yml"))
	}

	require.Len(t, files, len(rules)+len(tests), "rules + tests = files")
}

func TestRuleAndTestFilenames(t *testing.T) {
	files := ListRuleFilesWithTests()
	require.NotEmpty(t, files)

	for _, file := range files {
		t.Run(file, func(t *testing.T) {
			name := filepath.Base(file)

			left, right, found := strings.Cut(name, ".")

			require.True(t, found)
			require.NotContains(t, left, ".")
			require.NotContains(t, right, ".")

			if right != "yml" {
				require.Contains(t, extLangs, right)
			}
		})
	}
}

func TestEveryTestHasRule(t *testing.T) {
	tests := ListTestFiles()
	require.NotEmpty(t, tests)

	for _, test := range tests {
		t.Run(test, func(t *testing.T) {
			name := filepath.Base(test)
			dir := filepath.Dir(test)

			left, _, found := strings.Cut(name, ".")
			require.True(t, found)

			expected := fmt.Sprintf("%s.yml", left)

			require.FileExists(t, filepath.Join(dir, expected))
		})
	}
}

func TestEveryRuleHasTest(t *testing.T) {
	rules := ListRuleFiles()
	require.NotEmpty(t, rules)

	for _, rule := range rules {
		t.Run(rule, func(t *testing.T) {
			name := filepath.Base(rule)
			dir := filepath.Dir(rule)

			left, _, found := strings.Cut(name, ".")
			require.True(t, found)

			var actualTests []string
			var expectedTestNames []string

			for ext := range extLangs {
				expected := fmt.Sprintf("%s.%s", left, ext)

				if _, err := os.Stat(filepath.Join(dir, expected)); err == nil {
					actualTests = append(actualTests, expected)
					continue
				}

				expectedTestNames = append(expectedTestNames, expected)
			}

			require.NotEmpty(t, actualTests, "rule test not found within: %v", expectedTestNames)
		})
	}
}

func TestRuleSemgrepSchema(t *testing.T) {
	rules := ListRuleFiles()
	require.NotEmpty(t, rules)

	buf, err := os.ReadFile("schema_semgrep.yml")
	require.NoError(t, err)
	buf, err = yaml.YAMLToJSON(buf)
	require.NoError(t, err)
	semgrepSchemaLoader := gojsonschema.NewBytesLoader(buf)

	for _, rule := range rules {
		t.Run(rule, func(t *testing.T) {
			buf, err := os.ReadFile(rule)
			require.NoError(t, err)
			buf, err = yaml.YAMLToJSON(buf)
			require.NoError(t, err)
			docLoader := gojsonschema.NewBytesLoader(buf)

			result, err := gojsonschema.Validate(semgrepSchemaLoader, docLoader)
			require.NoError(t, err)

			var sb strings.Builder
			for _, err := range result.Errors() {
				sb.WriteString(err.String())
				sb.WriteRune('\n')
			}

			require.True(t, result.Valid(), sb.String())
		})
	}
}

func TestRuleGitLabSchema(t *testing.T) {
	rules := ListRuleFiles()
	require.NotEmpty(t, rules)

	buf, err := os.ReadFile("schema_gitlab.yml")
	require.NoError(t, err)
	buf, err = yaml.YAMLToJSON(buf)
	require.NoError(t, err)
	semgrepSchemaLoader := gojsonschema.NewBytesLoader(buf)

	for _, rule := range rules {
		t.Run(rule, func(t *testing.T) {
			buf, err := os.ReadFile(rule)
			require.NoError(t, err)
			buf, err = yaml.YAMLToJSON(buf)
			require.NoError(t, err)
			docLoader := gojsonschema.NewBytesLoader(buf)

			result, err := gojsonschema.Validate(semgrepSchemaLoader, docLoader)
			require.NoError(t, err)

			var sb strings.Builder
			for _, err := range result.Errors() {
				sb.WriteString(err.String())
				sb.WriteRune('\n')
			}

			require.True(t, result.Valid(), sb.String())
		})
	}
}

// TODO Test `ok:` and `ruleid:` assertions

// TODO Test rule language and test file extension match

// TODO Test spelling

// TODO Test formatting

// TODO Test license header for all files

// TODO Test for metavariable reuse using Jason's method
