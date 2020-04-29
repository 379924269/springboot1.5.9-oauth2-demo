package moe.cnkirito.security.oauth2.code.module.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 授权码
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OauthCode对象", description = "授权码")
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String authentication;


    public OauthCode() {
    }


    public OauthCode(
            String code
            ,
            String authentication
    ) {
        this.code = code;
        this.authentication = authentication;
    }
}
