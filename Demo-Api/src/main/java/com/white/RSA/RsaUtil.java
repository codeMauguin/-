/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.white.RSA;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mi
 */
@Component
public class RsaUtil {
    public final static String PRIVATE_KEY = "PRIVATE_KEY";
    public final static String PUBLIC_KEY = "PUBLIC_KEY";

    private String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public Map<Object, Object> getKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        byte[] publicBytes = pair.getPublic().getEncoded();
        byte[] privateBytes = pair.getPrivate().getEncoded();
        Map<Object, Object> rsa = new HashMap<>(2);
        rsa.put(PRIVATE_KEY, base64Encode(privateBytes));
        rsa.put(PUBLIC_KEY, base64Encode(publicBytes));
        return rsa;
    }

    private byte[] base64Decode(String str) {
        return Base64.getDecoder().decode(str);
    }

    public String decrypt(String data, String privateKey) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64Decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(keySpec));
            return new String(cipher.doFinal(base64Decode(data)));
        } catch (GeneralSecurityException ignored) {
        }
        return null;
    }

}