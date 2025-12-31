// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// https://github.com/semgrep/semgrep-rules/blob/release/java/lang/security/audit/crypto/use-of-rc2.java
import javax.crypto.Cipher;

class RC2 {

    String algo2 = "RC2";

    public void bad1() throws Exception {
        // ruleid: java_crypto_rule-UseOfRC2
        Cipher.getInstance("RC2");
    }

    public void bad2() throws Exception {
        // ruleid: java_crypto_rule-UseOfRC2
        Cipher.getInstance("RC2");
    }

    public void bad3() throws Exception {
        String algo = "RC2";
        // ruleid: java_crypto_rule-UseOfRC2
        Cipher.getInstance(algo);
    }

    public void bad4() throws Exception {
        // ruleid: java_crypto_rule-UseOfRC2
        Cipher.getInstance(algo2);
    }
    public void bad5() throws Exception {
        // ruleid: java_crypto_rule-UseOfRC2
        javax.crypto.Cipher.getInstance("RC2")
    }
    public void ok() throws Exception {
        // ok: java_crypto_rule-UseOfRC2
        Cipher.getInstance("AES/CBC/PKCS7PADDING");
    }
}
