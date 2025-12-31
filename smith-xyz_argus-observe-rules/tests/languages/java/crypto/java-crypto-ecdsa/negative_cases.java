package javacryptoecdsa;

import java.security.KeyPairGenerator;

public class NegativeCases {
    public void rsaInstead() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
    }
}
