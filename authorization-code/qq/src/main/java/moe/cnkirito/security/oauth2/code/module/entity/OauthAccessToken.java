package moe.cnkirito.security.oauth2.code.module.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 生成的token
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OauthAccessToken对象", description = "生成的token")
public class OauthAccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tokenId;

    private String token;

    private String authenticationId;

    private String userName;

    private String clientId;

    private String authentication;

    private String refreshToken;


    public OauthAccessToken() {
    }


    public OauthAccessToken(
            String tokenId
            ,
            String token
            ,
            String authenticationId
            ,
            String userName
            ,
            String clientId
            ,
            String authentication
            ,
            String refreshToken
    ) {
        this.tokenId = tokenId;
        this.token = token;
        this.authenticationId = authenticationId;
        this.userName = userName;
        this.clientId = clientId;
        this.authentication = authentication;
        this.refreshToken = refreshToken;
    }
}
