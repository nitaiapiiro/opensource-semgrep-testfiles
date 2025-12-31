package javacryptomd5;

import java.security.MessageDigest;

public class NegativeCases {
    public void sha256Instead() {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
    }
}
