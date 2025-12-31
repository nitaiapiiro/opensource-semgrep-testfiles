package javacryptokeylength;

import javax.crypto.KeyGenerator;
import java.security.KeyPairGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class ExampleCode {
    public void aesKeyGenerator() {
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(128);
        javax.crypto.SecretKey key = gen.generateKey();

        KeyGenerator gen2 = KeyGenerator.getInstance("AES");
        gen2.init(256);
        javax.crypto.SecretKey key2 = gen2.generateKey();
    }

    public void desKeyGenerator() {
        KeyGenerator gen = KeyGenerator.getInstance("DES");
        gen.init(56);
        javax.crypto.SecretKey key = gen.generateKey();
    }

    public void rsaKeyPairGenerator() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        java.security.KeyPair pair = gen.generateKeyPair();

        KeyPairGenerator gen2 = KeyPairGenerator.getInstance("RSA");
        gen2.initialize(4096);
        java.security.KeyPair pair2 = gen2.generateKeyPair();
    }

    public void ecKeyPairGenerator() {
        java.security.spec.ECGenParameterSpec ecSpec = new java.security.spec.ECGenParameterSpec("secp256r1");
        KeyPairGenerator gen = KeyPairGenerator.getInstance("EC");
        gen.initialize(256);
        java.security.KeyPair pair = gen.generateKeyPair();
    }

    public void secretKeySpecWithByteArray() {
        byte[] bytes = new byte[16];
        SecretKeySpec key = new SecretKeySpec(bytes, "AES");
        int len = bytes.length;
    }

    public void secretKeySpecWithSize() {
        byte[] bytes = new byte[32];
        SecretKeySpec key = new SecretKeySpec(bytes, "AES");
    }
}
