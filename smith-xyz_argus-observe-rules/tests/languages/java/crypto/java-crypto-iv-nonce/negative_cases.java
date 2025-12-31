package javacryptoivnonce;

import javax.crypto.Cipher;

public class NegativeCases {
    public void noIv() {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }
}
