package javacryptocipheraes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TestCode {
    public void basicAES() {
        byte[] key = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher1 = Cipher.getInstance("AES");
        Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Cipher cipher3 = Cipher.getInstance("AES/GCM/NoPadding");
        Cipher cipher4 = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public void aesGCMFull() {
        byte[] key = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), null);
        byte[] ciphertext = cipher.doFinal(plaintext);
    }
}
