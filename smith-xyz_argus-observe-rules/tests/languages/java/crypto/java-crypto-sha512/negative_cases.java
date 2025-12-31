package javacryptosha512;

import java.security.MessageDigest;

public class NegativeCases {
    public void sha256Instead() {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
    }

    public void sha1Instead() {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
    }
}
