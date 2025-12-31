# License: The GitLab Enterprise Edition (EE) license (the "EE License")

def test_safe_code():
    # ok: generic_injection_rule-BiDiTrojanSource
    normal_var = "Hello world"
    # ok: generic_injection_rule-BiDiTrojanSource
    safe_comment = "# This is a safe comment"

def test_rlo_attack():
    # ruleid: generic_injection_rule-BiDiTrojanSource
    malicious = "Hello‮ world"  # RLO (U+202E)
    
    # ruleid: generic_injection_rule-BiDiTrojanSource
    hidden = "print('Hello')‮ ('#Gotcha')"  # RLO to hide code as comment

def test_lro_attack():
    # ruleid: generic_injection_rule-BiDiTrojanSource
    var = "‭Reversed text"  # LRO (U+202D)

def test_multiple_bidi():
    # ruleid: generic_injection_rule-BiDiTrojanSource
    text = "Start‪ middle‬ end"  # LRE (U+202A) and PDF (U+202C)

# ok: generic_injection_rule-BiDiTrojanSource
safe_code = "This has no BiDi characters"

# ruleid: generic_injection_rule-BiDiTrojanSource
bidi_code = "This has a BiDi‮ character"

# Hiding malicious extension
# ruleid: generic_injection_rule-BiDiTrojanSource
safe_file = "document؜.txt.exe"  # Using ALM to visually separate extension

# Manipulating path display
# ruleid: generic_injection_rule-BiDiTrojanSource
path = "safe_folder‎/‏malicious"  # Combining LRM and RLM to alter path display

# Hiding code logic
# ruleid: generic_injection_rule-BiDiTrojanSource
if (x ‎>‏ y):  # Using LRM and RLM to alter operator appearance
    delete_files()
