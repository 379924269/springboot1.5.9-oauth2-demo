package moe.cnkirito.security.oauth2.code.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * jdbcClientDetailsService控制器
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "SystemClientdetailsController", description = "")
@RestController
@RequestMapping(value = "/systemClientdetailsController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SystemClientdetailsController {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "/listClientDetails", method = RequestMethod.GET)
    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET")
    public Object listClientDetails(@ApiParam(name = "token")
                                        @RequestParam(required = false, name = "token") String token) {
        return tokenStore.readAccessToken(token);
    }

}
