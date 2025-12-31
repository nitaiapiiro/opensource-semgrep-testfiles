package javacryptopasswordhashing;

import java.security.MessageDigest;

public class NegativeCases {
    public void notPasswordHashing() {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
    }
}
