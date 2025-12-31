// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/lang/security/audit/dangerous-groovy-shell.java

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class GroovyShellUsage {

    public static void test1(String uri, String file, String script) throws Exception {
        GroovyShell shell = new GroovyShell();
        String script2 = "println 'Hello from Groovy!'";

        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.evaluate(new File(file));
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.evaluate(new InputStreamReader(new FileInputStream(file)), "script1.groovy");
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.evaluate(script);
        // ok:java_inject_rule-DangerousGroovyShell
        shell.evaluate(script2);
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.evaluate(script, "script1.groovy", "test");
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.evaluate(new URI(uri));
        // ok:java_inject_rule-DangerousGroovyShell
        shell.evaluate("println 'Hello from Groovy!'");
    }

    public static void test2(String uri, String file, String script) throws Exception {
        GroovyShell shell = new GroovyShell();

        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.parse(new File(file));
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.parse(new InputStreamReader(new FileInputStream(file)), "test.groovy");
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.parse(new InputStreamReader(new FileInputStream(file)));
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.parse(script);
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.parse(script, "test.groovy");
        // ruleid:java_inject_rule-DangerousGroovyShell
        shell.parse(new URI(uri));

        String hardcodedScript = "println 'Hello from Groovy!'";
        // ok:java_inject_rule-DangerousGroovyShell
        shell.parse(hardcodedScript);

        // ok:java_inject_rule-DangerousGroovyShell
        shell.parse("println 'Hello from Groovy!'");
    }

    public static void test3(String uri, String file, String script, ClassLoader loader)
            throws Exception {
        GroovyClassLoader groovyLoader = new GroovyClassLoader(loader);

        // ruleid:java_inject_rule-DangerousGroovyShell
        groovyLoader.parseClass(new GroovyCodeSource(new File(file)), false);
        // ruleid:java_inject_rule-DangerousGroovyShell
        groovyLoader.parseClass(new InputStreamReader(new FileInputStream(file)), "test.groovy");
        // ruleid:java_inject_rule-DangerousGroovyShell
        groovyLoader.parseClass(script);
        // ruleid:java_inject_rule-DangerousGroovyShell
        groovyLoader.parseClass(script, "test.groovy");

        String hardcodedScript = "println 'Hello from Groovy!'";
        // ok:java_inject_rule-DangerousGroovyShell
        groovyLoader.parseClass(hardcodedScript);

        // ok:java_inject_rule-DangerousGroovyShell
        groovyLoader.parseClass("println 'Hello from Groovy!'");

        groovyLoader.close();
    }
}

class SafeDynamicEvaluation {
    private static final Set<String> ALLOWED_EXPRESSIONS = new HashSet<>(Arrays.asList(
            "println 'Hello World!'",
            "println 'Goodbye World!'"));

    public static void test4(String[] args, ClassLoader loader) throws Exception {
        GroovyShell shell = new GroovyShell();
        String userInput = args[0];
        GroovyClassLoader groovyLoader = new GroovyClassLoader(loader);

        // ruleid: java_inject_rule-DangerousGroovyShell
        shell.evaluate(userInput);
        // ruleid: java_inject_rule-DangerousGroovyShell
        shell.parse(userInput);
        // ruleid:java_inject_rule-DangerousGroovyShell
        groovyLoader.parseClass(userInput);

        // Validate the user input against the allowlist
        if (ALLOWED_EXPRESSIONS.contains(userInput)) {
            // ok: java_inject_rule-DangerousGroovyShell
            shell.evaluate(userInput);
            // ok: java_inject_rule-DangerousGroovyShell
            shell.parse(userInput);
            // ok:java_inject_rule-DangerousGroovyShell
            groovyLoader.parseClass(userInput);
        } else {
            groovyLoader.close();
            throw new IllegalArgumentException("Invalid or unauthorized command.");
        }

        groovyLoader.close();

    }

}

class TestRunnerGroovy {
    public static void main(String[] args) {
        try {
            String uri = "file:///path/testFile.groovy";
            String file = "testFile.groovy";
            String script = "println 'Dynamic execution'";

            ClassLoader loader = ClassLoader.getSystemClassLoader();

            // Running test methods from GroovyShellUsage
            GroovyShellUsage.test1(uri, file, script);
            GroovyShellUsage.test2(uri, file, script);
            GroovyShellUsage.test3(uri, file, script, loader);

            // Running test method from SafeDynamicEvaluation
            String[] userInput = { "println 'Hello World!'" };
            SafeDynamicEvaluation.test4(userInput, loader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
