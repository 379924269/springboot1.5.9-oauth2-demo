package moe.cnkirito.security.oauth2.code.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author huazai
 * @description 加密密码
 * @date 2020/5/26
 */
public class EncryptPasswordUtil {
    public static String encryptPassword(String needEncryptPassword) throws NoSuchAlgorithmException, InvalidKeyException {
        String sha256 = SHA.digestString(needEncryptPassword, SHA.SHA_256);
        return HMAC.bytesToHex(HMAC.encodeHmacSHA256(sha256.getBytes(), "GAT".getBytes()));
    }
}
