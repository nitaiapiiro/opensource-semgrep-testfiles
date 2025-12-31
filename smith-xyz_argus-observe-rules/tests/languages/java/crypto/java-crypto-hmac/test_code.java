package javacryptohmac;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TestCode {
    public void basicHMAC() {
        byte[] key = "secret key".getBytes();
        byte[] data = "test data".getBytes();

        Mac mac1 = Mac.getInstance("HmacMD5");
        Mac mac2 = Mac.getInstance("HmacSHA1");
        Mac mac3 = Mac.getInstance("HmacSHA256");
        Mac mac4 = Mac.getInstance("HmacSHA384");
        Mac mac5 = Mac.getInstance("HmacSHA512");
        Mac mac6 = Mac.getInstance("HmacSHA3-224");
        Mac mac7 = Mac.getInstance("HmacSHA3-256");
        Mac mac8 = Mac.getInstance("HmacSHA3-384");
        Mac mac9 = Mac.getInstance("HmacSHA3-512");
    }

    public void hmacSHA256Full() {
        byte[] key = "secret key".getBytes();
        byte[] data = "test data".getBytes();

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA256");
        mac.init(keySpec);
        mac.update(data);
        byte[] result = mac.doFinal();
    }

    public void hmacSHA512Full() {
        byte[] key = "secret key".getBytes();
        byte[] data = "test data".getBytes();

        Mac mac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA512");
        mac.init(keySpec);
        byte[] result = mac.doFinal(data);
    }

    public void hmacWithStringKey() {
        String keyString = "secret key";
        byte[] data = "test data".getBytes();

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(keyString.getBytes(), "HmacSHA256");
        mac.init(keySpec);
        mac.update(data);
        byte[] result = mac.doFinal();
    }
}
