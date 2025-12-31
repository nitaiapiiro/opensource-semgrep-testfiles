package javacryptosha1;

import java.security.MessageDigest;

public class TestCode {
    public void basicSHA1() {
        byte[] data = "test data".getBytes();

        MessageDigest digest1 = MessageDigest.getInstance("SHA1");
        digest1.update(data);
        byte[] hash1 = digest1.digest();

        MessageDigest digest2 = MessageDigest.getInstance("SHA-1");
        digest2.update(data);
        byte[] hash2 = digest2.digest();
    }
}
