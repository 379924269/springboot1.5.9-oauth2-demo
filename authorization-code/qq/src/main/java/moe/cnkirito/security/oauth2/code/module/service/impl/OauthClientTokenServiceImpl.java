package moe.cnkirito.security.oauth2.code.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.cnkirito.security.oauth2.code.module.entity.OauthClientToken;
import moe.cnkirito.security.oauth2.code.module.mapper.OauthClientTokenMapper;
import moe.cnkirito.security.oauth2.code.module.service.IOauthClientTokenService;
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
public class OauthClientTokenServiceImpl extends ServiceImpl<OauthClientTokenMapper, OauthClientToken> implements IOauthClientTokenService {

}
