package javacryptohardcodedkey;

import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class NegativeCases {
    public void randomKey() {
        SecureRandom rand = new SecureRandom();
        byte[] keyBytes = new byte[16];
        rand.nextBytes(keyBytes);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
    }
}
