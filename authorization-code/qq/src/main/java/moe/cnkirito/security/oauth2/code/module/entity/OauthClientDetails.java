package moe.cnkirito.security.oauth2.code.module.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 客户端信息
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OauthClientDetails对象", description = " 客户端信息")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;


    public OauthClientDetails() {
    }


    public OauthClientDetails(
            String clientId
            ,
            String resourceIds
            ,
            String clientSecret
            ,
            String scope
            ,
            String authorizedGrantTypes
            ,
            String webServerRedirectUri
            ,
            String authorities
            ,
            Integer accessTokenValidity
            ,
            Integer refreshTokenValidity
            ,
            String additionalInformation
            ,
            String autoapprove
    ) {
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.webServerRedirectUri = webServerRedirectUri;
        this.authorities = authorities;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.additionalInformation = additionalInformation;
        this.autoapprove = autoapprove;
    }
}
