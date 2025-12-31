package javacryptocipher3des;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

public class TestCode {
    public void basic3DES() {
        byte[] key = new byte[24];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher1 = Cipher.getInstance("DESede");
        cipher1.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "DESede"));
        byte[] ciphertext1 = cipher1.doFinal(plaintext);

        Cipher cipher2 = Cipher.getInstance("TripleDES");
        cipher2.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "TripleDES"));
        byte[] ciphertext2 = cipher2.doFinal(plaintext);
    }

    public void desedeWithModes() {
        byte[] key = new byte[24];
        byte[] iv = new byte[8];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher1.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "DESede"), new IvParameterSpec(iv));
        byte[] ciphertext1 = cipher1.doFinal(plaintext);

        Cipher cipher2 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        Cipher cipher3 = Cipher.getInstance("DESede/CFB/PKCS5Padding");
        Cipher cipher4 = Cipher.getInstance("DESede/OFB/PKCS5Padding");
    }

    public void tripleDESDecrypt() {
        byte[] key = new byte[24];
        byte[] ciphertext = "encrypted".getBytes();

        Cipher cipher = Cipher.getInstance("TripleDES");
        cipher.init(Cipher.DECRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "TripleDES"));
        byte[] plaintext = cipher.doFinal(ciphertext);
    }
}
