package com.sample.springboot_boilerplate.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class CryptoUtils {
    private static final String SECRET_KEY = "YfjIOUwGa4DJ7uE4A7RWSX6Zlnu3f7rB1N2y8I5o048=";
    private static final String ALGORITHM = "AES";


    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error during encryption", e);
        }
    }


    public static String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error during decryption", e);
        }
    }


    private static SecretKeySpec getSecretKey() {
        // Decode the Base64 key into bytes
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);


        // Wrap bytes in a SecretKeySpec object with AES algorithm
        return new SecretKeySpec(decodedKey, ALGORITHM);
    }
}
