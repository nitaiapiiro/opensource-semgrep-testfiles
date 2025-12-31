package javacryptobouncycastlenegative;

import java.security.MessageDigest;
import javax.crypto.Cipher;

public class NegativeCases {
    public void standardJavaCrypto() {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        Cipher cipher = Cipher.getInstance("AES");
    }

    public void noBouncyCastle() {
        String provider = "SUN";
        MessageDigest.getInstance("SHA-256", provider);
    }
}
