package moe.cnkirito.security.oauth2.code.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GetUserInfoVo", description = "getUserInfo接口返回对象")
public class GetUserInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像（头像地址）")
    private String avatar;

    @ApiModelProperty(value = "邮箱地址，和emm一致，可以用来登录")
    private String email;

    @ApiModelProperty(value = "手机号码,可以用来登录")
    private String mobile;
}
