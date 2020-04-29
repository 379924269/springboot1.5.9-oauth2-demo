package moe.cnkirito.security.oauth2.code.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.cnkirito.security.oauth2.code.module.entity.UserRole;
import moe.cnkirito.security.oauth2.code.module.mapper.UserRoleMapper;
import moe.cnkirito.security.oauth2.code.module.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
