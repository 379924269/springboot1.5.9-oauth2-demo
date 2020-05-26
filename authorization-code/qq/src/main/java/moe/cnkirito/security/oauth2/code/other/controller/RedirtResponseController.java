package moe.cnkirito.security.oauth2.code.other.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器 操作token
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "RedirtResponseController", description = "跳转地址返回相关信息")
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RedirtResponseController {

    @RequestMapping(value = "/redirt/responseAuthorizationCode", method = RequestMethod.GET)
    @ApiOperation(value = "跳转地址返回授权码相关信息", response = IdVo.class)
    public Object responseAuthorizationCode(@ApiParam(name = "code", value = "code")
                            @RequestParam(required = false, name = "code") String code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authorizationCode", code);
        return jsonObject.toString();
    }

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @RequestMapping(value = "/redirt/listClientDetails", method = RequestMethod.GET)
    @ApiOperation(value = "listClientDetails", response = IdVo.class)
    public Object ccc(@ApiParam(name = "code", value = "code")
                                            @RequestParam(required = false, name = "code") String code) {
       return jdbcClientDetailsService.listClientDetails();
    }

    @RequestMapping("/custom/getAuthorizationParam")
    public Object getAuthorizationParam(String clientId) throws Exception {
        return jdbcClientDetailsService.loadClientByClientId(clientId);
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ApiOperation(value = "未登录接口（或叫登录界面跳转接口）", hidden = true)
//    public ModelAndView login() {
//        return new ModelAndView(new RedirectView("http://192.168.0.151:1234/login"));
//    }
}

