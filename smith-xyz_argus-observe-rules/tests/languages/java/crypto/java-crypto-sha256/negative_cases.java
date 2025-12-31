package javacryptosha256;

import java.security.MessageDigest;

public class NegativeCases {
    public void md5Instead() {
        MessageDigest digest = MessageDigest.getInstance("MD5");
    }
}
