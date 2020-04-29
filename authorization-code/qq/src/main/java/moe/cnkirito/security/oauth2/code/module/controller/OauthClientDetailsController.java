package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.OauthClientDetails;
import moe.cnkirito.security.oauth2.code.module.service.IOauthClientDetailsService;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import moe.cnkirito.security.oauth2.code.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客户端信息  前端控制器
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "OauthClientDetailsController", description = " 客户端信息")
@RestController
@RequestMapping(value = "/module/oauthClientDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OauthClientDetailsController {
    @Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有 客户端信息", notes = "查询所有 客户端信息")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<OauthClientDetails> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(OauthClientDetails::getClientId, search);
        }
        queryWrapper.lambda().orderByDesc(OauthClientDetails::getClientId);
        return oauthClientDetailsService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询 客户端信息详情", notes = "查询 客户端信息详情", httpMethod = "GET")
    public OauthClientDetails findById(@ApiParam(name = "id", value = " 客户端信息id", required = true) @PathVariable("id") Integer id) {
        return oauthClientDetailsService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改 客户端信息", notes = "通过id修改 客户端信息")
    public void updateById(OauthClientDetails oauthClientDetails) {
        oauthClientDetailsService.updateById(oauthClientDetails);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加 客户端信息", notes = "添加 客户端信息", response = IdVo.class)
    public Object save(

            @ApiParam(name = "clientId", value = "")
            @RequestParam(required = false, name = "clientId") String clientId
            ,
            @ApiParam(name = "resourceIds", value = "")
            @RequestParam(required = false, name = "resourceIds") String resourceIds
            ,
            @ApiParam(name = "clientSecret", value = "")
            @RequestParam(required = false, name = "clientSecret") String clientSecret
            ,
            @ApiParam(name = "scope", value = "")
            @RequestParam(required = false, name = "scope") String scope
            ,
            @ApiParam(name = "authorizedGrantTypes", value = "")
            @RequestParam(required = false, name = "authorizedGrantTypes") String authorizedGrantTypes
            ,
            @ApiParam(name = "webServerRedirectUri", value = "")
            @RequestParam(required = false, name = "webServerRedirectUri") String webServerRedirectUri
            ,
            @ApiParam(name = "authorities", value = "")
            @RequestParam(required = false, name = "authorities") String authorities
            ,
            @ApiParam(name = "accessTokenValidity", value = "")
            @RequestParam(required = false, name = "accessTokenValidity") Integer accessTokenValidity
            ,
            @ApiParam(name = "refreshTokenValidity", value = "")
            @RequestParam(required = false, name = "refreshTokenValidity") Integer refreshTokenValidity
            ,
            @ApiParam(name = "additionalInformation", value = "")
            @RequestParam(required = false, name = "additionalInformation") String additionalInformation
            ,
            @ApiParam(name = "autoapprove", value = "")
            @RequestParam(required = false, name = "autoapprove") String autoapprove
    ) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails(

                clientId, resourceIds, clientSecret, scope, authorizedGrantTypes, webServerRedirectUri, authorities, accessTokenValidity, refreshTokenValidity, additionalInformation, autoapprove);
        oauthClientDetailsService.save(oauthClientDetails);
        return oauthClientDetails.getClientId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除 客户端信息", notes = "修改 客户端信息")
    public void deleteById(@ApiParam(name = "id", value = " 客户端信息id", required = true) @PathVariable("id") Integer id) {
        oauthClientDetailsService.removeById(id);
    }

}
