package moe.cnkirito.security.oauth2.code.exception.token;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Description 异常格式
 * @Author wwz
 * @Date 2019/07/30
 * @Param
 * @Return
 */
@JsonSerialize(using = MyOAuthExceptionJacksonSerializer.class)
public class MyOAuth2Exception extends OAuth2Exception {
    public MyOAuth2Exception(String msg, Throwable t) {
        super(msg, t);

    }
    public MyOAuth2Exception(String msg) {
        super(msg);

    }
}
