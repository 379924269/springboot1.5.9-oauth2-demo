package moe.cnkirito.security.oauth2.code.module.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 刷新token
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OauthRefreshToken对象", description = "刷新token")
public class OauthRefreshToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tokenId;

    private String token;

    private String authentication;


    public OauthRefreshToken() {
    }


    public OauthRefreshToken(
            String tokenId
            ,
            String token
            ,
            String authentication
    ) {
        this.tokenId = tokenId;
        this.token = token;
        this.authentication = authentication;
    }
}
