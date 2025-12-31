package javacryptociphermodes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.GCMParameterSpec;

public class TestCode {
    public void cipherModes() {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher1 = Cipher.getInstance("AES/ECB/PKCS5Padding");
        Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Cipher cipher3 = Cipher.getInstance("AES/CFB/NoPadding");
        Cipher cipher4 = Cipher.getInstance("AES/OFB/NoPadding");
        Cipher cipher5 = Cipher.getInstance("AES/CTR/NoPadding");
        Cipher cipher6 = Cipher.getInstance("AES/GCM/NoPadding");
        Cipher cipher7 = Cipher.getInstance("AES/CCM/NoPadding");
        Cipher cipher8 = Cipher.getInstance("AES/XTS/NoPadding");
    }

    public void cbcMode() {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
    }

    public void gcmMode() {
        byte[] key = new byte[16];
        byte[] nonce = new byte[12];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), new GCMParameterSpec(128, nonce));
    }

    public void cfbMode() {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
    }

    public void ofbMode() {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
    }

    public void ctrMode() {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
    }

    public void ccmMode() {
        byte[] key = new byte[16];
        byte[] nonce = new byte[12];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("AES/CCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"), new GCMParameterSpec(128, nonce));
    }

    public void xtsMode() {
        byte[] key = new byte[32];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("AES/XTS/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "AES"));
    }
}
