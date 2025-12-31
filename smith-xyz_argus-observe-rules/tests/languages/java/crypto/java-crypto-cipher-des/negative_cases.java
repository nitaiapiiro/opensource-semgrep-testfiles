package javacryptocipherdes;

import javax.crypto.Cipher;

public class NegativeCases {
    public void aesInstead() {
        Cipher cipher = Cipher.getInstance("AES");
    }
}
