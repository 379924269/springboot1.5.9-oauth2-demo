package moe.cnkirito.security.oauth2.code.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.cnkirito.security.oauth2.code.module.entity.OauthAccessToken;
import moe.cnkirito.security.oauth2.code.module.mapper.OauthAccessTokenMapper;
import moe.cnkirito.security.oauth2.code.module.service.IOauthAccessTokenService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 生成的token 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Service
public class OauthAccessTokenServiceImpl extends ServiceImpl<OauthAccessTokenMapper, OauthAccessToken> implements IOauthAccessTokenService {

}
