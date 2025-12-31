package javacryptokeyderivation;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class ExampleCode {
    public void pbkdf2SHA1() {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    }

    public void pbkdf2SHA256() {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    }

    public void pbkdf2Full() {
        char[] password = "password".toCharArray();
        byte[] salt = new byte[16];
        int iterations = 10000;
        int keyLength = 256;

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        javax.crypto.SecretKey key = factory.generateSecret(spec);
    }
}
