package javacryptodigitalsignatures;

import java.security.Signature;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class TestCode {
    public void rsaSignatures() {
        Signature sig1 = Signature.getInstance("SHA256withRSA");
        Signature sig2 = Signature.getInstance("SHA512withRSA");
        Signature sig3 = Signature.getInstance("SHA256withRSA/PSS");
        Signature sig4 = Signature.getInstance("SHA512withRSA/PSS");
    }

    public void ecdsaSignatures() {
        Signature sig1 = Signature.getInstance("SHA256withECDSA");
        Signature sig2 = Signature.getInstance("SHA512withECDSA");
    }

    public void dsaSignatures() {
        Signature sig1 = Signature.getInstance("SHA256withDSA");
        Signature sig2 = Signature.getInstance("SHA512withDSA");
    }

    public void eddsaSignatures() {
        Signature sig1 = Signature.getInstance("Ed25519");
        Signature sig2 = Signature.getInstance("Ed448");
    }

    public void rsaSign() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        KeyPair pair = gen.generateKeyPair();
        byte[] data = "test data".getBytes();

        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(pair.getPrivate());
        sig.update(data);
        byte[] signature = sig.sign();
    }

    public void rsaVerify() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        KeyPair pair = gen.generateKeyPair();
        byte[] data = "test data".getBytes();
        byte[] signature = new byte[256];

        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(pair.getPublic());
        sig.update(data);
        boolean verified = sig.verify(signature);
    }

    public void ecdsaSign() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("EC");
        gen.initialize(256);
        KeyPair pair = gen.generateKeyPair();
        byte[] data = "test data".getBytes();

        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initSign(pair.getPrivate());
        sig.update(data);
        byte[] signature = sig.sign();
    }

    public void rsaPssSign() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        KeyPair pair = gen.generateKeyPair();
        byte[] data = "test data".getBytes();

        Signature sig = Signature.getInstance("SHA256withRSA/PSS");
        sig.initSign(pair.getPrivate());
        sig.update(data);
        byte[] signature = sig.sign();
    }

    public void ed25519Sign() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("Ed25519");
        gen.initialize(null);
        KeyPair pair = gen.generateKeyPair();
        byte[] data = "test data".getBytes();

        Signature sig = Signature.getInstance("Ed25519");
        sig.initSign(pair.getPrivate());
        sig.update(data);
        byte[] signature = sig.sign();
    }
}
