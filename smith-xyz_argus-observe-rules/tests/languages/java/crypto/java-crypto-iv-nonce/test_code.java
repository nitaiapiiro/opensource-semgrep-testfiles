package javacryptoivnonce;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;

public class TestCode {
    public void ivParameterSpec() {
        byte[] iv = new byte[16];
        IvParameterSpec ivParams = new IvParameterSpec(iv);
    }

    public void gcmParameterSpec() {
        byte[] nonce = new byte[12];
        GCMParameterSpec gcmParams = new GCMParameterSpec(128, nonce);
    }

    public void ivWithCipher() {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        byte[] plaintext = "test data".getBytes();

        SecureRandom rand = new SecureRandom();
        rand.nextBytes(iv);
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), ivParams);
    }

    public void staticIv() {
        byte[] key = new byte[16];
        byte[] iv = "static_iv_value".getBytes();
        byte[] plaintext = "test data".getBytes();

        IvParameterSpec ivParams = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), ivParams);
    }

    public void gcmNonce() {
        byte[] key = new byte[16];
        byte[] nonce = new byte[12];
        byte[] plaintext = "test data".getBytes();

        SecureRandom rand = new SecureRandom();
        rand.nextBytes(nonce);
        GCMParameterSpec gcmParams = new GCMParameterSpec(128, nonce);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), gcmParams);
    }

    public void staticIvInline() {
        byte[] key = new byte[16];
        byte[] plaintext = "test data".getBytes();

        IvParameterSpec ivParams = new IvParameterSpec("static".getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), ivParams);
    }
}
