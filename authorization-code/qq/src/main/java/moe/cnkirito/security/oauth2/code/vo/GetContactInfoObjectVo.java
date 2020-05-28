package moe.cnkirito.security.oauth2.code.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author huazai
 * @since 2019-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GetContactInfoObjectVo", description = "联系人数组和自己")
public class GetContactInfoObjectVo {
    @ApiModelProperty(value = "用户自己")
    private Integer userId;

    @ApiModelProperty(value = "联系人对象")
    private List<GetContactInfoVo> friend;
}
