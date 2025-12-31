package javacryptocipherdes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TestCode {
    public void basicDES() {
        byte[] key = new byte[8];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher1 = Cipher.getInstance("DES");
        Cipher cipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
        Cipher cipher3 = Cipher.getInstance("DES/CBC/PKCS5Padding");
        Cipher cipher4 = Cipher.getInstance("DESede");
        Cipher cipher5 = Cipher.getInstance("TripleDES");
    }

    public void desEncrypt() {
        byte[] key = new byte[8];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DES"));
        byte[] ciphertext = cipher.doFinal(plaintext);
    }
}
