// License: The GitLab Enterprise Edition (EE) license (the "EE License")

public class BidiTest {
    public void testSafeCode() {
        // ok: generic_injection_rule-BiDiTrojanSource
        String normalVar = "Hello world";
        // ok: generic_injection_rule-BiDiTrojanSource
        String safeComment = "// This is a safe comment";
    }

    public void testRLOAttack() {
        // ruleid: generic_injection_rule-BiDiTrojanSource
        String malicious = "Hello‮ world";  // RLO (U+202E)
        
        // ruleid: generic_injection_rule-BiDiTrojanSource
        String hidden = "System.out.println('Hello')‮ //Gotcha";  // RLO to hide code as comment
    }

    public void testLROAttack() {
        // ruleid: generic_injection_rule-BiDiTrojanSource
        String reversed = "‭Reversed text";  // LRO (U+202D)
    }

    public void testMultipleBidi() {
        // ruleid: generic_injection_rule-BiDiTrojanSource
        String text = "Start‪ middle‬ end";  // LRE (U+202A) and PDF (U+202C)
    }

    // ok: generic_injection_rule-BiDiTrojanSource
    String safeVar = "This has no BiDi characters";

    // ruleid: generic_injection_rule-BiDiTrojanSource
    String bidiVar = "This has a BiDi‮ character";

    // Hiding malicious extension
    // ruleid: generic_injection_rule-BiDiTrojanSource
    String hiddenFile = "document؜.txt.exe"; 

    // Manipulating path display
    // ruleid: generic_injection_rule-BiDiTrojanSource
    String hiddenPath = "safe_folder‎/‏malicious"; 

    // Hiding code logic
    public void checkCondition() {
        // ruleid: generic_injection_rule-BiDiTrojanSource
        if (x ‎>‏ y) { // Using LRM and RLM to alter operator appearance
            // Malicious code here, e.g., 
            try {
            Runtime.getRuntime().exec("cmd /c calc.exe"); // Example of a simple command execution
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }
}