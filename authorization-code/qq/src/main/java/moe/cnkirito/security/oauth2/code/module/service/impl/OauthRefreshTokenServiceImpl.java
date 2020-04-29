package moe.cnkirito.security.oauth2.code.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.cnkirito.security.oauth2.code.module.entity.OauthRefreshToken;
import moe.cnkirito.security.oauth2.code.module.mapper.OauthRefreshTokenMapper;
import moe.cnkirito.security.oauth2.code.module.service.IOauthRefreshTokenService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 刷新token 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Service
public class OauthRefreshTokenServiceImpl extends ServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken> implements IOauthRefreshTokenService {

}
