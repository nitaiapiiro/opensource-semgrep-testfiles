package javacryptodigitalsignatures;

import java.security.MessageDigest;

public class NegativeCases {
    public void messageDigestInstead() {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
    }
}
