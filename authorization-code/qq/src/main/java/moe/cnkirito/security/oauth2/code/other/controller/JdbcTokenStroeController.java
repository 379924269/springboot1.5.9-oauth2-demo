package moe.cnkirito.security.oauth2.code.other.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * <p>
 * 前端控制器 操作token
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "JdbcTokenStroeController", description = "操作token的controller")
@RestController
@RequestMapping(value = "/module/JdbcTokenStroeController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JdbcTokenStroeController {
    @Autowired
    private JdbcTokenStore jdbcTokenStore;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "添加", response = IdVo.class)
    public void removeToken(@ApiParam(name = "token", value = "token")
                            @RequestParam(required = false, name = "token") String token) {
        Collection<OAuth2AccessToken> xx = jdbcTokenStore.findTokensByClientId("aiqiyi");
        for (OAuth2AccessToken oAuth2AccessToken : xx) {
            String gettoken = oAuth2AccessToken.getValue();
            System.out.println("gettoken = " + gettoken);
        }

        jdbcTokenStore.removeAccessToken(token);
    }


}
