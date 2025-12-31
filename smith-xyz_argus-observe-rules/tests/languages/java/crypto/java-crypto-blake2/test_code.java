package javacryptoblake2;

import java.security.MessageDigest;

public class TestCode {
    public void basicBlake2() {
        byte[] data = "test data".getBytes();

        MessageDigest digest1 = MessageDigest.getInstance("BLAKE2B-512");
        digest1.update(data);
        byte[] hash1 = digest1.digest();

        MessageDigest digest2 = MessageDigest.getInstance("BLAKE2B-256");
        digest2.update(data);
        byte[] hash2 = digest2.digest();

        MessageDigest digest3 = MessageDigest.getInstance("BLAKE2S-256");
        digest3.update(data);
        byte[] hash3 = digest3.digest();
    }

    public void blake2WithEllipsis() {
        byte[] data = "test data".getBytes();
        MessageDigest digest = MessageDigest.getInstance("BLAKE2B-256");
        digest.update(data);
        byte[] otherData = "more data".getBytes();
        digest.update(otherData);
        byte[] hash = digest.digest();
    }

    public void blake2WithBytes() {
        byte[] data = "test data".getBytes();
        MessageDigest digest = MessageDigest.getInstance("BLAKE2S-256");
        digest.update(data);
        byte[] bytes = digest.digest();
    }
}
