package javacryptocipheraes;

import javax.crypto.Cipher;

public class NegativeCases {
    public void desInstead() {
        Cipher cipher = Cipher.getInstance("DES");
    }
}
