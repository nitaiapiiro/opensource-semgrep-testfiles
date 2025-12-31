package javacryptosha3;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class TestCode {
    public void basicSHA3() {
        byte[] data = "test data".getBytes();

        MessageDigest digest1 = MessageDigest.getInstance("SHA3-224");
        digest1.update(data);
        byte[] hash1 = digest1.digest();

        MessageDigest digest2 = MessageDigest.getInstance("SHA3-256");
        digest2.update(data);
        byte[] hash2 = digest2.digest();

        MessageDigest digest3 = MessageDigest.getInstance("SHA3-384");
        digest3.update(data);
        byte[] hash3 = digest3.digest();

        MessageDigest digest4 = MessageDigest.getInstance("SHA3-512");
        digest4.update(data);
        byte[] hash4 = digest4.digest();
    }

    public void sha3WithHex() {
        byte[] data = "test data".getBytes();
        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        digest.update(data);
        byte[] bytes = digest.digest();
        String hex = DatatypeConverter.printHexBinary(bytes);
    }

    public void sha3WithEllipsis() {
        byte[] data = "test data".getBytes();
        MessageDigest digest = MessageDigest.getInstance("SHA3-512");
        digest.update(data);
        byte[] otherData = "more data".getBytes();
        digest.update(otherData);
        byte[] hash = digest.digest();
    }
}
