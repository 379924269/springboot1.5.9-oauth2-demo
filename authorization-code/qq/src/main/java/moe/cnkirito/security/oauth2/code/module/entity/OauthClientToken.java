package moe.cnkirito.security.oauth2.code.module.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OauthClientToken对象", description = "")
public class OauthClientToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tokenId;

    private String token;

    private String authenticationId;

    private String userName;

    private String clientId;


    public OauthClientToken() {
    }


    public OauthClientToken(
            String tokenId
            ,
            String token
            ,
            String authenticationId
            ,
            String userName
            ,
            String clientId
    ) {
        this.tokenId = tokenId;
        this.token = token;
        this.authenticationId = authenticationId;
        this.userName = userName;
        this.clientId = clientId;
    }
}
