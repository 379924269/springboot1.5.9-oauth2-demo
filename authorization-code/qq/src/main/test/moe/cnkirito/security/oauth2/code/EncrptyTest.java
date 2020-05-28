package moe.cnkirito.security.oauth2.code;

import moe.cnkirito.security.oauth2.code.util.EncryptPasswordUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author huazai
 * @description 加密测试
 * @date 2020/5/26
 */
public class EncrptyTest {
    @Test
    public void test() throws InvalidKeyException, NoSuchAlgorithmException {
        String xx = EncryptPasswordUtil.encryptPassword("@dnp5");
        System.out.println("xx = " + xx);
        String md5xx = DigestUtils.md5Hex(xx);
        System.out.println("md5xx = " + md5xx);
    }
}
