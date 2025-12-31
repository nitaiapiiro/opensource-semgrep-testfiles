package javacryptocipherblowfish;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

public class TestCode {
    public void basicBlowfish() {
        byte[] key = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher1 = Cipher.getInstance("Blowfish");
        cipher1.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "Blowfish"));
        byte[] ciphertext1 = cipher1.doFinal(plaintext);
    }

    public void blowfishWithModes() {
        byte[] key = new byte[16];
        byte[] iv = new byte[8];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher1 = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
        cipher1.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "Blowfish"), new IvParameterSpec(iv));
        byte[] ciphertext1 = cipher1.doFinal(plaintext);

        Cipher cipher2 = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        Cipher cipher3 = Cipher.getInstance("Blowfish/CFB/PKCS5Padding");
        Cipher cipher4 = Cipher.getInstance("Blowfish/OFB/PKCS5Padding");
    }

    public void blowfishDecrypt() {
        byte[] key = new byte[16];
        byte[] ciphertext = "encrypted".getBytes();

        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "Blowfish"));
        byte[] plaintext = cipher.doFinal(ciphertext);
    }
}
