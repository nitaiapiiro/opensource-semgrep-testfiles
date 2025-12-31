package javacryptorsa;

import java.security.KeyPairGenerator;

public class NegativeCases {
    public void ecInstead() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("EC");
    }
}
