package moe.cnkirito.security.oauth2.code.exception.token;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @Description 定义异常MyOAuth2Exception的序列化
 * @Author wwz
 * @Date 2019/07/11
 * @Param
 * @Return
 */
public class MyOAuthExceptionJacksonSerializer extends StdSerializer<MyOAuth2Exception> {

    protected MyOAuthExceptionJacksonSerializer() {
        super(MyOAuth2Exception.class);
    }

    @Override
    public void serialize(MyOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        if (value.getSummary().contains("Invalid authorization code")) {
            jgen.writeStartObject();
            jgen.writeObjectField("code", value.getHttpErrorCode());
            jgen.writeStringField("message", "无效的授权码");
            jgen.writeEndObject();
            return;
        }
        if (value.getSummary().contains("Token was not recognised")) {
            jgen.writeStartObject();
            jgen.writeObjectField("code", value.getHttpErrorCode());
            jgen.writeStringField("message", "令牌无法识别");
            jgen.writeEndObject();
            return;
        }


        jgen.writeStartObject();
        jgen.writeObjectField("code", value.getHttpErrorCode());
        jgen.writeStringField("message", value.getSummary());
        jgen.writeEndObject();
    }
}
