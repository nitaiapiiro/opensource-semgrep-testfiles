package javacryptohardcodedkey;

import javax.crypto.spec.SecretKeySpec;

public class ExampleCode {
    public void hardcodedStringKey() {
        SecretKeySpec key1 = new SecretKeySpec("mySecretKey".getBytes(), "AES");
        SecretKeySpec key2 = new SecretKeySpec("hardcoded".getBytes(), "AES");
    }

    public void hardcodedKeyWithVariable() {
        String keyString = "secret";
        SecretKeySpec key1 = new SecretKeySpec(keyString.getBytes(), "AES");
        SecretKeySpec key2 = new SecretKeySpec(keyString.getBytes(), "DES");
    }

    public void hardcodedKeyWithAlgorithm() {
        SecretKeySpec key = new SecretKeySpec("key".getBytes(), "AES");
    }
}
