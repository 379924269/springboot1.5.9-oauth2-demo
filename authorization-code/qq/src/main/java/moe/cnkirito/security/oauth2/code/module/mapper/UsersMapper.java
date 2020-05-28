package moe.cnkirito.security.oauth2.code.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import moe.cnkirito.security.oauth2.code.module.entity.Users;
import moe.cnkirito.security.oauth2.code.vo.GetContactInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author huazai
 * @since 2020-05-14
 */
public interface UsersMapper extends BaseMapper<Users> {
    List<GetContactInfoVo> findContactInfo(@Param("userId") Integer userId);
}
