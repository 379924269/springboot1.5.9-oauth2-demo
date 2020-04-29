package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.OauthRefreshToken;
import moe.cnkirito.security.oauth2.code.module.service.IOauthRefreshTokenService;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import moe.cnkirito.security.oauth2.code.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 刷新token  前端控制器
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "OauthRefreshTokenController", description = "刷新token")
@RestController
@RequestMapping(value = "/module/oauthRefreshToken", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OauthRefreshTokenController {
    @Autowired
    private IOauthRefreshTokenService oauthRefreshTokenService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有刷新token", notes = "查询所有刷新token")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<OauthRefreshToken> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<OauthRefreshToken> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(OauthRefreshToken::getTokenId, search);
        }
        queryWrapper.lambda().orderByDesc(OauthRefreshToken::getTokenId);
        return oauthRefreshTokenService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询刷新token详情", notes = "查询刷新token详情", httpMethod = "GET")
    public OauthRefreshToken findById(@ApiParam(name = "id", value = "刷新tokenid", required = true) @PathVariable("id") Integer id) {
        return oauthRefreshTokenService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改刷新token", notes = "通过id修改刷新token")
    public void updateById(OauthRefreshToken oauthRefreshToken) {
        oauthRefreshTokenService.updateById(oauthRefreshToken);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加刷新token", notes = "添加刷新token", response = IdVo.class)
    public Object save(

            @ApiParam(name = "tokenId", value = "")
            @RequestParam(required = false, name = "tokenId") String tokenId
            ,
            @ApiParam(name = "token", value = "")
            @RequestParam(required = false, name = "token") String token
            ,
            @ApiParam(name = "authentication", value = "")
            @RequestParam(required = false, name = "authentication") String authentication
    ) {
        OauthRefreshToken oauthRefreshToken = new OauthRefreshToken(

                tokenId, token, authentication);
        oauthRefreshTokenService.save(oauthRefreshToken);
        return oauthRefreshToken.getTokenId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除刷新token", notes = "修改刷新token")
    public void deleteById(@ApiParam(name = "id", value = "刷新tokenid", required = true) @PathVariable("id") Integer id) {
        oauthRefreshTokenService.removeById(id);
    }

}
