package javacryptosha256;

import java.security.MessageDigest;

public class TestCode {
    public void basicSHA256() {
        byte[] data = "test data".getBytes();

        MessageDigest digest1 = MessageDigest.getInstance("SHA-256");
        digest1.update(data);
        byte[] hash1 = digest1.digest();

        MessageDigest digest2 = MessageDigest.getInstance("SHA256");
        digest2.update(data);
        byte[] hash2 = digest2.digest();
    }
}
