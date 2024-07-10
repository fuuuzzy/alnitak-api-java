package com.ztinfg.utils;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;

public final class JweUtil {

    public static String createToken(String payload, String key) throws JoseException {
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setPayload(payload);
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128GCMKW);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
        jwe.setKey(getKey(key));
        return jwe.getCompactSerialization();
    }

    public static String verifyToken(String token, String key) throws JoseException {
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.PERMIT,
                KeyManagementAlgorithmIdentifiers.A128GCMKW));
        jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.PERMIT,
                ContentEncryptionAlgorithmIdentifiers.AES_128_GCM));
        jwe.setKey(getKey(key));
        jwe.setCompactSerialization(token);
        return jwe.getPayload();
    }

    private static AesKey getKey(String key) {
        return new AesKey(key.getBytes());
    }

    private JweUtil() {
    }
}