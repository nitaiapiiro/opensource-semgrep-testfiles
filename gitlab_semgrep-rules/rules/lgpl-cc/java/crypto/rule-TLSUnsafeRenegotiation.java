// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/problem-based-packs/insecure-transport/java-stdlib/tls-renegotiation.java

class Bad {
    public void bad1() {
        // ruleid: java_crypto_rule-TLSUnsafeRenegotiation
        java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", true);
    }

    public void bad2() {
        // ruleid: java_crypto_rule-TLSUnsafeRenegotiation
        java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
    }

    public void bad3() {
        // ruleid: java_crypto_rule-TLSUnsafeRenegotiation
        java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", Boolean.TRUE);
    }
}

class Ok {
    public void ok1() {
        // ok: java_crypto_rule-TLSUnsafeRenegotiation
        java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", false);
    }

    public void ok2() {
        // ok: java_crypto_rule-TLSUnsafeRenegotiation
        java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "false");
    }

    public void ok3() {
        // ok: java_crypto_rule-TLSUnsafeRenegotiation
        java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", Boolean.FALSE);
    }
}
