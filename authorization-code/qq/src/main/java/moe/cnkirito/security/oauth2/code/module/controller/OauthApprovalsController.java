package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.OauthApprovals;
import moe.cnkirito.security.oauth2.code.module.service.IOauthApprovalsService;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import moe.cnkirito.security.oauth2.code.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "OauthApprovalsController", description = "")
@RestController
@RequestMapping(value = "/module/oauthApprovals", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OauthApprovalsController {
    @Autowired
    private IOauthApprovalsService oauthApprovalsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<OauthApprovals> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<OauthApprovals> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(OauthApprovals::getClientId, search);
        }
        queryWrapper.lambda().orderByDesc(OauthApprovals::getClientId);
        return oauthApprovalsService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET")
    public OauthApprovals findById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        return oauthApprovalsService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改", notes = "通过id修改")
    public void updateById(OauthApprovals oauthApprovals) {
        oauthApprovalsService.updateById(oauthApprovals);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "添加", response = IdVo.class)
    public Object save(

            @ApiParam(name = "userId", value = "")
            @RequestParam(required = false, name = "userId") String userId
            ,
            @ApiParam(name = "clientId", value = "")
            @RequestParam(required = false, name = "clientId") String clientId
            ,
            @ApiParam(name = "scope", value = "")
            @RequestParam(required = false, name = "scope") String scope
            ,
            @ApiParam(name = "status", value = "")
            @RequestParam(required = false, name = "status") String status
            ,
            @ApiParam(name = "expiresAt", value = "")
            @RequestParam(required = false, name = "expiresAt") LocalDateTime expiresAt
            ,
            @ApiParam(name = "lastModifiedAt", value = "")
            @RequestParam(required = false, name = "lastModifiedAt") LocalDateTime lastModifiedAt
    ) {
        OauthApprovals oauthApprovals = new OauthApprovals(

                userId, clientId, scope, status, expiresAt, lastModifiedAt);
        oauthApprovalsService.save(oauthApprovals);
        return oauthApprovals.getClientId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除", notes = "修改")
    public void deleteById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        oauthApprovalsService.removeById(id);
    }

}
