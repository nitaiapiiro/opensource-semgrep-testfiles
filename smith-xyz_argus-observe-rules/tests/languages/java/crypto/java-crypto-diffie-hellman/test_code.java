package javacryptodiffiehellman;

import javax.crypto.KeyAgreement;
import java.security.KeyPairGenerator;
import java.security.spec.DHParameterSpec;
import java.security.KeyPair;

public class TestCode {
    public void dhKeyPairGenerator() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("DH");
        DHParameterSpec dhSpec = new DHParameterSpec(null, null, 1024);
        gen.initialize(dhSpec);
        KeyPair pair = gen.generateKeyPair();
    }

    public void dhKeyAgreement() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("DH");
        gen.initialize(2048);
        KeyPair pair1 = gen.generateKeyPair();
        KeyPair pair2 = gen.generateKeyPair();

        KeyAgreement agreement = KeyAgreement.getInstance("DH");
        agreement.init(pair1.getPrivate());
        agreement.doPhase(pair2.getPublic(), true);
        byte[] secret = agreement.generateSecret();
    }

    public void dhKeyAgreementWithAlgorithm() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("DH");
        gen.initialize(2048);
        KeyPair pair1 = gen.generateKeyPair();
        KeyPair pair2 = gen.generateKeyPair();

        KeyAgreement agreement = KeyAgreement.getInstance("DiffieHellman");
        agreement.init(pair1.getPrivate());
        agreement.doPhase(pair2.getPublic(), true);
        javax.crypto.SecretKey secretKey = agreement.generateSecret("AES");
    }

    public void dhKeyAgreementFull() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("DH");
        gen.initialize(2048);
        KeyPair pair1 = gen.generateKeyPair();
        KeyPair pair2 = gen.generateKeyPair();

        KeyAgreement agreement = KeyAgreement.getInstance("DH");
        agreement.init(pair1.getPrivate());
        agreement.doPhase(pair2.getPublic(), true);
        javax.crypto.SecretKey secretKey = agreement.generateSecret("AES");
    }
}
