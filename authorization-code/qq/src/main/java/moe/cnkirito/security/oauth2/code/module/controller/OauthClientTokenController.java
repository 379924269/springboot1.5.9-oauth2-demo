package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.OauthClientToken;
import moe.cnkirito.security.oauth2.code.module.service.IOauthClientTokenService;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import moe.cnkirito.security.oauth2.code.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "OauthClientTokenController", description = "")
@RestController
@RequestMapping(value = "/module/oauthClientToken", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OauthClientTokenController {
    @Autowired
    private IOauthClientTokenService oauthClientTokenService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<OauthClientToken> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<OauthClientToken> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(OauthClientToken::getClientId, search);
        }
        queryWrapper.lambda().orderByDesc(OauthClientToken::getClientId);
        return oauthClientTokenService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET")
    public OauthClientToken findById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        return oauthClientTokenService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改", notes = "通过id修改")
    public void updateById(OauthClientToken oauthClientToken) {
        oauthClientTokenService.updateById(oauthClientToken);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "添加", response = IdVo.class)
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
    ) {
        OauthClientToken oauthClientToken = new OauthClientToken(

                tokenId, token, authenticationId, userName, clientId);
        oauthClientTokenService.save(oauthClientToken);
        return oauthClientToken.getClientId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除", notes = "修改")
    public void deleteById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        oauthClientTokenService.removeById(id);
    }

}
