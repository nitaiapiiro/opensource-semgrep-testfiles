package javacryptorsa;

import java.security.KeyPairGenerator;
import javax.crypto.Cipher;

public class TestCode {
    public void rsaKeyPairGenerator() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        java.security.KeyPair pair = gen.generateKeyPair();
    }

    public void rsaCipher() {
        Cipher cipher1 = Cipher.getInstance("RSA");
        Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        Cipher cipher3 = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
    }

    public void rsaWithKeySize() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(4096);
        java.security.KeyPair pair = gen.generateKeyPair();
    }
}
