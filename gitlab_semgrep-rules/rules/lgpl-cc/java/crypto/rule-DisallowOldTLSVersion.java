// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/problem-based-packs/insecure-transport/java-stdlib/disallow-old-tls-versions2.java

class InsecureTLS {
    public void InsecureTLS1() {
        // ruleid: java_crypto_rule-DisallowOldTLSVersion
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1.1,TLSv1.2,TLSv1.3");
    }

    public void InsecureTLS2() {
        // ruleid: java_crypto_rule-DisallowOldTLSVersion
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1.2,TLSv1.3,SSLv3");
    }

    public void InsecureSSL() {
        // ruleid: java_crypto_rule-DisallowOldTLSVersion
        java.lang.System.setProperty("jdk.tls.client.protocols", "SSLv3");
    }

    public void InsecureTLS3() {
        // ruleid: java_crypto_rule-DisallowOldTLSVersion
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1.1");
    }

    public void InsecureTLS4() {
        // ruleid: java_crypto_rule-DisallowOldTLSVersion
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1");
    }

}

class SecureTLS {
    public void SecureTLS1() {
        // ok: java_crypto_rule-DisallowOldTLSVersion
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1.2,TLSv1.3");
    }

    public void SecureTLS2() {
        // ok: java_crypto_rule-DisallowOldTLSVersion
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
    }

    public void SecureTLS3() {
        // ok: java_crypto_rule-DisallowOldTLSVersion
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1.3");
    }
}
