package moe.cnkirito.security.oauth2.code.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.cnkirito.security.oauth2.code.module.entity.Role;
import moe.cnkirito.security.oauth2.code.module.mapper.RoleMapper;
import moe.cnkirito.security.oauth2.code.module.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
