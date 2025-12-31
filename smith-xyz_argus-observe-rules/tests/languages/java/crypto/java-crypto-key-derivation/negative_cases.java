package javacryptokeyderivation;

import javax.crypto.SecretKeyFactory;

public class NegativeCases {
    public void notPBKDF2() {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
    }
}
