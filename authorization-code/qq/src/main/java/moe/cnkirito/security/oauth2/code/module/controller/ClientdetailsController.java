package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.Clientdetails;
import moe.cnkirito.security.oauth2.code.module.service.IClientdetailsService;
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
@Api(value = "ClientdetailsController", description = "")
@RestController
@RequestMapping(value = "/module/clientdetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClientdetailsController {
    @Autowired
    private IClientdetailsService clientdetailsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<Clientdetails> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<Clientdetails> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(Clientdetails::getAppId, search);
        }
        queryWrapper.lambda().orderByDesc(Clientdetails::getAppId);
        return clientdetailsService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET")
    public Clientdetails findById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        return clientdetailsService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改", notes = "通过id修改")
    public void updateById(Clientdetails clientdetails) {
        clientdetailsService.updateById(clientdetails);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "添加", response = IdVo.class)
    public Object save(

            @ApiParam(name = "appId", value = "")
            @RequestParam(required = false, name = "appId") String appId
            ,
            @ApiParam(name = "resourceIds", value = "")
            @RequestParam(required = false, name = "resourceIds") String resourceIds
            ,
            @ApiParam(name = "appSecret", value = "")
            @RequestParam(required = false, name = "appSecret") String appSecret
            ,
            @ApiParam(name = "scope", value = "")
            @RequestParam(required = false, name = "scope") String scope
            ,
            @ApiParam(name = "grantTypes", value = "")
            @RequestParam(required = false, name = "grantTypes") String grantTypes
            ,
            @ApiParam(name = "redirectUrl", value = "")
            @RequestParam(required = false, name = "redirectUrl") String redirectUrl
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
            @ApiParam(name = "autoApproveScopes", value = "")
            @RequestParam(required = false, name = "autoApproveScopes") String autoApproveScopes
    ) {
        Clientdetails clientdetails = new Clientdetails(

                appId, resourceIds, appSecret, scope, grantTypes, redirectUrl, authorities, accessTokenValidity, refreshTokenValidity, additionalInformation, autoApproveScopes);
        clientdetailsService.save(clientdetails);
        return clientdetails.getAppId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除", notes = "修改")
    public void deleteById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        clientdetailsService.removeById(id);
    }

}
