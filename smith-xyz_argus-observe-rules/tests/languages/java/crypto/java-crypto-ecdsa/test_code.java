package javacryptoecdsa;

import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;

public class TestCode {
    public void ecKeyPairGenerator() {
        KeyPairGenerator gen1 = KeyPairGenerator.getInstance("EC");
        KeyPairGenerator gen2 = KeyPairGenerator.getInstance("ECDSA");
    }

    public void ecdsaSignatures() {
        Signature sig1 = Signature.getInstance("SHA256withECDSA");
        Signature sig2 = Signature.getInstance("SHA512withECDSA");
    }

    public void ecKeyGeneration() {
        java.security.spec.ECGenParameterSpec ecSpec = new java.security.spec.ECGenParameterSpec("secp256r1");
        KeyPairGenerator gen = KeyPairGenerator.getInstance("EC");
        gen.initialize(ecSpec);
        java.security.KeyPair pair = gen.generateKeyPair();
    }

    public void ecdsaSign() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("EC");
        gen.initialize(256);
        java.security.KeyPair pair = gen.generateKeyPair();
        byte[] data = "test data".getBytes();

        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initSign(pair.getPrivate());
        sig.update(data);
        byte[] signature = sig.sign();
    }
}
