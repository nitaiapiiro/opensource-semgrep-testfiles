package javacryptocipherrc4;

import javax.crypto.Cipher;

public class TestCode {
    public void basicRC4() {
        byte[] key = new byte[16];
        byte[] plaintext = "test data".getBytes();

        Cipher cipher1 = Cipher.getInstance("RC4");
        cipher1.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "RC4"));
        byte[] ciphertext1 = cipher1.doFinal(plaintext);

        Cipher cipher2 = Cipher.getInstance("ARCFOUR");
        cipher2.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "ARCFOUR"));
        byte[] ciphertext2 = cipher2.doFinal(plaintext);

        Cipher cipher3 = Cipher.getInstance("ARC4");
        cipher3.init(Cipher.DECRYPT_MODE, new javax.crypto.spec.SecretKeySpec(key, "ARC4"));
        byte[] plaintext3 = cipher3.doFinal(ciphertext2);
    }
}
