package javacryptocipher3des;

import javax.crypto.Cipher;

public class NegativeCases {
    public void aesInstead() {
        Cipher cipher = Cipher.getInstance("AES");
    }

    public void desInstead() {
        Cipher cipher = Cipher.getInstance("DES");
    }
}
