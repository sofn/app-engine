package com.junesix.common.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author jolestar
 */
public class RSAEncrypter {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    private RSAEncrypter(String privateKeyStr, String publicKeyStr) {
        privateKey = loadPrivateKey(privateKeyStr);
        publicKey = loadPublicKey(publicKeyStr);
    }

    private static RSAPrivateKey loadPrivateKey(String privateKeyStr) {
        try {
            byte[] buffer = Base64.decodeBase64(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new EncrypterException(e);
        }
    }

    private static RSAPublicKey loadPublicKey(String publicKeyStr) {
        try {
            byte[] buffer = Base64.decodeBase64(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new EncrypterException(e);
        }
    }

    public byte[] decrypt(String message) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(Hex.decodeHex(message.toCharArray()));
        } catch (Exception e) {
            String msg = "decrypt error, data:" + message;
            throw new EncrypterException(msg, e);
        }
    }

    public String decryptAsString(String message) {
        return new String(decrypt(message));
    }

    public String encrypt(String message) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Hex.encodeHexString(cipher.doFinal(message.getBytes()));
        } catch (Exception e) {
            String msg = "encrypt error, data:" + message;
            throw new EncrypterException(msg, e);
        }
    }

}
