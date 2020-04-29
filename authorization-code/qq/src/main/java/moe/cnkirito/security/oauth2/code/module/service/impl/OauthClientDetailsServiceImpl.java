package moe.cnkirito.security.oauth2.code.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.cnkirito.security.oauth2.code.module.entity.OauthClientDetails;
import moe.cnkirito.security.oauth2.code.module.mapper.OauthClientDetailsMapper;
import moe.cnkirito.security.oauth2.code.module.service.IOauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端信息 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

}
