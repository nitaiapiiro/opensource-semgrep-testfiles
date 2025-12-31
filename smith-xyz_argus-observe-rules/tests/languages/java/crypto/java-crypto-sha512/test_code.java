package javacryptosha512;

import java.security.MessageDigest;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

public class TestCode {
    public void basicSHA512() {
        byte[] data = "test data".getBytes();

        MessageDigest digest1 = MessageDigest.getInstance("SHA-512");
        digest1.update(data);
        byte[] hash1 = digest1.digest();

        MessageDigest digest2 = MessageDigest.getInstance("SHA512");
        digest2.update(data);
        byte[] hash2 = digest2.digest();

        MessageDigest digest3 = MessageDigest.getInstance("SHA512/224");
        digest3.update(data);
        byte[] hash3 = digest3.digest();

        MessageDigest digest4 = MessageDigest.getInstance("SHA512/256");
        digest4.update(data);
        byte[] hash4 = digest4.digest();
    }

    public void sha512WithHex() {
        byte[] data = "test data".getBytes();
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.update(data);
        byte[] bytes = digest.digest();
        String hex = DatatypeConverter.printHexBinary(bytes);
    }

    public void sha512WithBase64() {
        byte[] data = "test data".getBytes();
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.update(data);
        byte[] bytes = digest.digest();
        String base64 = Base64.getEncoder().encodeToString(bytes);
    }

    public void sha512WithEllipsis() {
        byte[] data = "test data".getBytes();
        MessageDigest digest = MessageDigest.getInstance("SHA512");
        digest.update(data);
        byte[] otherData = "more data".getBytes();
        digest.update(otherData);
        byte[] hash = digest.digest();
    }
}
