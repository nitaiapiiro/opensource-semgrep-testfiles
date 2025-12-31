// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/lang/security/audit/crypto/use-of-rc4.java

import javax.crypto.Cipher;

class RC4 {
    String algo2 = "RC4";

    public void bad1() {
        // ruleid: java_crypto_rule-UseOfRC4
        Cipher.getInstance("RC4");
    }

    public void bad2() throws Exception {
        String algo = "RC4";
        // ruleid: java_crypto_rule-UseOfRC4
        Cipher.getInstance(algo);
    }

    public void bad3() throws Exception {
        // ruleid: java_crypto_rule-UseOfRC4
        Cipher.getInstance(algo2);
    }

    public void bad4() throws Exception {
        // ruleid: java_crypto_rule-UseOfRC4
        javax.crypto.Cipher.getInstance("RC4");
    }

    public void ok() {
        // ok: java_crypto_rule-UseOfRC4
        Cipher.getInstance("AES/CBC/PKCS7PADDING");
    }
}
