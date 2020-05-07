package moe.cnkirito.security.oauth2.code.other.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.User;
import moe.cnkirito.security.oauth2.code.module.service.IUserService;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import moe.cnkirito.security.oauth2.code.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.*;

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
        jdbcTokenStore.removeAccessToken(token);
    }


}
