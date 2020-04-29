package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.OauthAccessToken;
import moe.cnkirito.security.oauth2.code.module.service.IOauthAccessTokenService;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import moe.cnkirito.security.oauth2.code.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 生成的token  前端控制器
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "OauthAccessTokenController", description = "生成的token")
@RestController
@RequestMapping(value = "/module/oauthAccessToken", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OauthAccessTokenController {
    @Autowired
    private IOauthAccessTokenService oauthAccessTokenService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有生成的token", notes = "查询所有生成的token")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<OauthAccessToken> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<OauthAccessToken> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(OauthAccessToken::getClientId, search);
        }
        queryWrapper.lambda().orderByDesc(OauthAccessToken::getClientId);
        return oauthAccessTokenService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询生成的token详情", notes = "查询生成的token详情", httpMethod = "GET")
    public OauthAccessToken findById(@ApiParam(name = "id", value = "生成的tokenid", required = true) @PathVariable("id") Integer id) {
        return oauthAccessTokenService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改生成的token", notes = "通过id修改生成的token")
    public void updateById(OauthAccessToken oauthAccessToken) {
        oauthAccessTokenService.updateById(oauthAccessToken);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加生成的token", notes = "添加生成的token", response = IdVo.class)
    public Object save(

            @ApiParam(name = "tokenId", value = "")
            @RequestParam(required = false, name = "tokenId") String tokenId
            ,
            @ApiParam(name = "token", value = "")
            @RequestParam(required = false, name = "token") String token
            ,
            @ApiParam(name = "authenticationId", value = "")
            @RequestParam(required = false, name = "authenticationId") String authenticationId
            ,
            @ApiParam(name = "userName", value = "")
            @RequestParam(required = false, name = "userName") String userName
            ,
            @ApiParam(name = "clientId", value = "")
            @RequestParam(required = false, name = "clientId") String clientId
            ,
            @ApiParam(name = "authentication", value = "")
            @RequestParam(required = false, name = "authentication") String authentication
            ,
            @ApiParam(name = "refreshToken", value = "")
            @RequestParam(required = false, name = "refreshToken") String refreshToken
    ) {
        OauthAccessToken oauthAccessToken = new OauthAccessToken(

                tokenId, token, authenticationId, userName, clientId, authentication, refreshToken);
        oauthAccessTokenService.save(oauthAccessToken);
        return oauthAccessToken.getClientId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除生成的token", notes = "修改生成的token")
    public void deleteById(@ApiParam(name = "id", value = "生成的tokenid", required = true) @PathVariable("id") Integer id) {
        oauthAccessTokenService.removeById(id);
    }

}
