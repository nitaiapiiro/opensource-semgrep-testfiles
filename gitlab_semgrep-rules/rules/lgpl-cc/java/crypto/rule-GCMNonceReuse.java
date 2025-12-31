// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
package crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class GCMNonceReuse
{
    public static final int GCM_TAG_LENGTH = 16;
    public static final String IV1String = "ab0123456789";
    public static final byte[] IV1 = IV1String.getBytes();
    public static final byte[] IV2 = new byte[]{97,98,48,49,50,51,52,53,54,55,56,57};

    private static byte[] IV3;

    private static SecretKey secretKey;

    GCMParameterSpec gcmParameterSpec;


    public GCMNonceReuse() {

        IV3 = IV1String.getBytes();

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //curl --location 'http://localhost:8080/ServletSample/GcmHardcodedIV?method=encrypt&input=Lorem%20ipsum%20dolor%20sit%20amet%2C%20consectetur%20adipiscing%20elit%2C%20sed%20do%20eiusmod%20tempor%20incididunt%20ut%20labore%20et%20dolore%20magna%20aliqua.'
    public String encrypt(String clearText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        //ruleid: java_crypto_rule-GCMNonceReuse
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV1);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(clearText.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }

    //curl --location 'http://localhost:8080/ServletSample/GcmHardcodedIV?method=encrypt2&input=Lorem%20ipsum%20dolor%20sit%20amet%2C%20consectetur%20adipiscing%20elit%2C%20sed%20do%20eiusmod%20tempor%20incididunt%20ut%20labore%20et%20dolore%20magna%20aliqua.'
    public String encrypt2(String clearText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        //ruleid: java_crypto_rule-GCMNonceReuse
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV2);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(clearText.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }

    //curl --location 'http://localhost:8080/ServletSample/GcmHardcodedIV?method=encrypt3&input=Lorem%20ipsum%20dolor%20sit%20amet%2C%20consectetur%20adipiscing%20elit%2C%20sed%20do%20eiusmod%20tempor%20incididunt%20ut%20labore%20et%20dolore%20magna%20aliqua.'
    public String encrypt3(String clearText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        //ruleid: java_crypto_rule-GCMNonceReuse
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV1String.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(clearText.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }

    //curl --location 'http://localhost:8080/ServletSample/GcmHardcodedIV?method=encryptSecure&input=Lorem%20ipsum%20dolor%20sit%20amet%2C%20consectetur%20adipiscing%20elit%2C%20sed%20do%20eiusmod%20tempor%20incididunt%20ut%20labore%20et%20dolore%20magna%20aliqua.'
    public String encryptSecure(String clearText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        // Generate a new, random IV for each encryption operation
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[12];
        // GCM standard recommends a 12-byte (96-bit) IV
        secureRandom.nextBytes(iv);

        //ok: java_crypto_rule-GCMNonceReuse
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(clearText.getBytes());

        // Prepend the IV to the ciphertext to ensure it's available for decryption
        byte[] cipherTextWithIv = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, cipherTextWithIv, 0, iv.length);
        System.arraycopy(cipherText, 0, cipherTextWithIv, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(cipherTextWithIv);
    }

    //curl --location 'http://localhost:8080/ServletSample/GcmHardcodedIV?method=decrypt&input=ciphertext>'
    public String decrypt(String cipherText) throws Exception {
        cipherText = URLDecoder.decode(cipherText, StandardCharsets.UTF_8.toString()).replace(" ", "+");;

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        //ruleid: java_crypto_rule-GCMNonceReuse
        gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV3);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decryptedText = cipher.doFinal(decoded);

        return new String(decryptedText);
    }

    //curl --location 'http://localhost:8080/ServletSample/GcmHardcodedIV?method=decryptSecure&input=ciphertext>'
    public static String decryptSecure(String encryptedText) throws Exception {
        encryptedText = URLDecoder.decode(encryptedText, StandardCharsets.UTF_8.toString()).replace(" ", "+");

        // Decode the base64-encoded string
        byte[] decodedCipherText = Base64.getDecoder().decode(encryptedText);

        // Extract the IV from the beginning of the ciphertext
        byte[] iv = new byte[12];

        // Extract the actual ciphertext
        byte[] cipherText = new byte[decodedCipherText.length - 12];
        System.arraycopy(decodedCipherText, 12, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        //ok: java_crypto_rule-GCMNonceReuse
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

        // Decrypt the ciphertext
        byte[] clearTextBytes = cipher.doFinal(cipherText);

        return new String(clearTextBytes);
    }

}