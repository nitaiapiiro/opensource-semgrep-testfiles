package javacryptociphermodes;

import javax.crypto.Cipher;

public class NegativeCases {
    public void noModeSpecified() {
        Cipher cipher = Cipher.getInstance("AES");
    }
}
