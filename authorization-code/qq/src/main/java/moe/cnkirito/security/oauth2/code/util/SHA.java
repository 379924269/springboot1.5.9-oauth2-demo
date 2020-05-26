package moe.cnkirito.security.oauth2.code.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;

/**
 * description: sha
 *
 * @author: 华仔
 * @date: 2020/5/26
 */
public class SHA {
    public final static String MD5 = "MD5";
    public final static String NONE = "NONE";
    public final static String SHA_256 = "SHA-256";
    public final static String SHA_512 = "SHA-512";
    public final static String SHA_384 = "SHA-384";

    /**
     * description:
     *
     * @param password : 需要加密的原始密码
     * @param alg      : 算法名称
     * @return : java.lang.String
     * @throws NoSuchAlgorithmException 当加密算法不可用时抛出此异常
     */
    public static String digestString(String password, String alg) throws NoSuchAlgorithmException {
        String newPass;
        if (alg == null || MD5.equals(alg)) {
            newPass = DigestUtils.md5Hex(password);
        } else if (NONE.equals(alg)) {
            newPass = password;
        } else if (SHA_256.equals(alg)) {
            newPass = DigestUtils.sha256Hex(password);
        } else if (SHA_384.equals(alg)) {
            newPass = DigestUtils.sha384Hex(password);
        } else if (SHA_512.equals(alg)) {
            newPass = DigestUtils.sha512Hex(password);
        } else {
            newPass = DigestUtils.shaHex(password);
        }
        return newPass;
    }

    /**
     * 加密密码算法，默认的加密算法是MD5
     *
     * @param password 未加密的密码
     * @return String 加密后的密码
     */
    public static String digestPassword(String password) {
        try {
            if (password != null && !"".equals(password)) {
                return digestString(password, MD5);
            } else
                return null;
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("Security error: " + nsae);
        }
    }
}
