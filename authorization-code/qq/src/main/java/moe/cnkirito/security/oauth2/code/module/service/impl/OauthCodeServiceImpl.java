package moe.cnkirito.security.oauth2.code.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.cnkirito.security.oauth2.code.module.entity.OauthCode;
import moe.cnkirito.security.oauth2.code.module.mapper.OauthCodeMapper;
import moe.cnkirito.security.oauth2.code.module.service.IOauthCodeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 授权码 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Service
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeMapper, OauthCode> implements IOauthCodeService {

}
