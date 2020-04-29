package moe.cnkirito.security.oauth2.code.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "Clientdetails对象", description = "")
public class Clientdetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("appId")
    private String appId;

    @TableField("resourceIds")
    private String resourceIds;

    @TableField("appSecret")
    private String appSecret;

    private String scope;

    @TableField("grantTypes")
    private String grantTypes;

    @TableField("redirectUrl")
    private String redirectUrl;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    @TableField("additionalInformation")
    private String additionalInformation;

    @TableField("autoApproveScopes")
    private String autoApproveScopes;


    public Clientdetails() {
    }


    public Clientdetails(
            String appId
            ,
            String resourceIds
            ,
            String appSecret
            ,
            String scope
            ,
            String grantTypes
            ,
            String redirectUrl
            ,
            String authorities
            ,
            Integer accessTokenValidity
            ,
            Integer refreshTokenValidity
            ,
            String additionalInformation
            ,
            String autoApproveScopes
    ) {
        this.appId = appId;
        this.resourceIds = resourceIds;
        this.appSecret = appSecret;
        this.scope = scope;
        this.grantTypes = grantTypes;
        this.redirectUrl = redirectUrl;
        this.authorities = authorities;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.additionalInformation = additionalInformation;
        this.autoApproveScopes = autoApproveScopes;
    }
}
