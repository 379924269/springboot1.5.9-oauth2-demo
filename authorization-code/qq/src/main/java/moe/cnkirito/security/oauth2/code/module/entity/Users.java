package moe.cnkirito.security.oauth2.code.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value = "Users对象", description = "")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "代理id")
    @TableId(value = "userid", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @TableField("employeeid")
    @ApiModelProperty(value = "工号")
    private String employeeId;

    @ApiModelProperty(value = "头像（头像地址）")
    private String avatar;

    @ApiModelProperty(value = "密码MD5，保留兼容语音通话")
    private String secret;

    @ApiModelProperty(value = "SHA256计算的密码hash值")
    private String password;

    @TableField("defaultchannel")
    @ApiModelProperty(value = "默认频道")
    private Integer defaultChannel;

    @ApiModelProperty(value = "0 禁用 1 启用 2 到期")
    private Integer enable;

    @ApiModelProperty(value = "用户类型，“1”表示记时注册用户，“2表示终身用户”")
    private Integer type;

    @TableField("createid")
    @ApiModelProperty(value = "创建者（管理员）的ID")
    private Integer createId;

    @TableField("maturitytime")
    @ApiModelProperty(value = "用户到期时间")
    private LocalDateTime maturiTytime;

    @TableField("imeiid")
    @ApiModelProperty(value = "用户手机串号ID外键")
    private Integer imeiId;

    @TableField("lastlogintime")
    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "邮箱地址，和emm一致，可以用来登录")
    private String email;

    @ApiModelProperty(value = "用户标志，目前用来作为客户端本地数据库的密码")
    private String uuid;

    @ApiModelProperty(value = "公钥")
    private String pubkey;

    @TableField("sdcardid")
    @ApiModelProperty(value = "加密卡")
    private Integer sdcardId;

    @ApiModelProperty(value = "手机号码,可以用来登录")
    private String mobile;

    @ApiModelProperty(value = "个性签名")
    private String signInfo;

    @ApiModelProperty(value = "1男2女0未知")
    private Boolean gender;

    @ApiModelProperty(value = "所属部门Id")
    @TableField("departId")
    private Integer departId;

    @ApiModelProperty(value = "创建时间，单位秒")
    private Integer created;

    @ApiModelProperty(value = "更新时间，单位秒")
    private Integer updated;

    @TableField("verificationcode")
    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    @TableField("loginerrorcount")
    @ApiModelProperty(value = "登录错误次数")
    private Integer loginErrorCount;

    @TableField("codeexpirationtime")
    @ApiModelProperty(value = "验证码过期时间")
    private Long codeExpirationTime;


    public Users() {
    }


    public Users(Integer userId, String username, String nickname, String employeeId, String avatar, String secret, String password, Integer defaultChannel, Integer enable, Integer type, Integer createId, LocalDateTime maturiTytime, Integer imeiId, LocalDateTime lastLoginTime, String email, String uuid, String pubkey, Integer sdcardId, String mobile, String signInfo, Boolean gender, Integer departId, Integer created, Integer updated, String verificationCode, Integer loginErrorCount, Long codeExpirationTime) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.employeeId = employeeId;
        this.avatar = avatar;
        this.secret = secret;
        this.password = password;
        this.defaultChannel = defaultChannel;
        this.enable = enable;
        this.type = type;
        this.createId = createId;
        this.maturiTytime = maturiTytime;
        this.imeiId = imeiId;
        this.lastLoginTime = lastLoginTime;
        this.email = email;
        this.uuid = uuid;
        this.pubkey = pubkey;
        this.sdcardId = sdcardId;
        this.mobile = mobile;
        this.signInfo = signInfo;
        this.gender = gender;
        this.departId = departId;
        this.created = created;
        this.updated = updated;
        this.verificationCode = verificationCode;
        this.loginErrorCount = loginErrorCount;
        this.codeExpirationTime = codeExpirationTime;
    }
}
