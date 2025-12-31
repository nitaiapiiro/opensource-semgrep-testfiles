package javacryptoblake2;

import java.security.MessageDigest;

public class NegativeCases {
    public void sha256Instead() {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
    }

    public void md5Instead() {
        MessageDigest digest = MessageDigest.getInstance("MD5");
    }
}
