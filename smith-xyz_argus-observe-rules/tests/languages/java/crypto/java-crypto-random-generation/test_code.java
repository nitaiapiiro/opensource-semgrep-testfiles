package javacryptorandomgeneration;

import java.security.SecureRandom;
import java.util.Random;

public class TestCode {
    public void secureRandom() {
        SecureRandom rand1 = SecureRandom.getInstance("SHA1PRNG");
        SecureRandom rand2 = SecureRandom.getInstanceStrong();
        SecureRandom rand3 = new SecureRandom();
    }

    public void secureRandomNextBytes() {
        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        rand.nextBytes(bytes);
    }

    public void secureRandomNew() {
        SecureRandom rand = new SecureRandom();
        byte[] bytes = new byte[16];
        rand.nextBytes(bytes);
    }

    public void nonCryptoRandom() {
        Random rand = new Random();
        double value = Math.random();
    }
}
