package javacryptobouncycastle;

import java.security.Security;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import java.security.KeyPairGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ExampleCode {
    public void addProvider() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public void insertProviderAt() {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    public void md4Digest() {
        MessageDigest digest = MessageDigest.getInstance("MD4");
    }

    public void md2Digest() {
        MessageDigest digest = MessageDigest.getInstance("MD2");
    }

    public void twofishCipher() {
        Cipher cipher = Cipher.getInstance("Twofish");
    }

    public void camelliaCipher() {
        Cipher cipher = Cipher.getInstance("Camellia/CBC/PKCS5Padding");
    }

    public void chacha20Cipher() {
        Cipher cipher = Cipher.getInstance("ChaCha20-Poly1305");
    }

    public void salsa20Cipher() {
        Cipher cipher = Cipher.getInstance("Salsa20");
    }

    public void elgamalKeyPair() {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("ElGamal");
    }

    public void bouncyCastleClasses() {
        org.bouncycastle.crypto.digests.MD4Digest md4 = new org.bouncycastle.crypto.digests.MD4Digest();
        org.bouncycastle.crypto.engines.TwofishEngine engine = new org.bouncycastle.crypto.engines.TwofishEngine();
        org.bouncycastle.crypto.generators.Argon2BytesGenerator argon2 = new org.bouncycastle.crypto.generators.Argon2BytesGenerator();
    }
}
