package javacryptomd5;

import java.security.MessageDigest;

public class ExampleCode {
    public void basicMD5() {
        byte[] data = "test data".getBytes();

        MessageDigest digest1 = MessageDigest.getInstance("MD5");
        digest1.update(data);
        byte[] hash1 = digest1.digest();

        MessageDigest digest2 = MessageDigest.getInstance("md5");
        digest2.update(data);
        byte[] hash2 = digest2.digest();
    }

    public void md5WithEllipsis() {
        byte[] data = "test data".getBytes();
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(data);
        byte[] otherData = "more data".getBytes();
        digest.update(otherData);
        byte[] hash = digest.digest();
    }
}
