package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.OauthCode;
import moe.cnkirito.security.oauth2.code.module.service.IOauthCodeService;
import moe.cnkirito.security.oauth2.code.vo.IdVo;
import moe.cnkirito.security.oauth2.code.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 授权码  前端控制器
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "OauthCodeController", description = "授权码")
@RestController
@RequestMapping(value = "/module/oauthCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OauthCodeController {
    @Autowired
    private IOauthCodeService oauthCodeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有授权码", notes = "查询所有授权码")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<OauthCode> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<OauthCode> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(OauthCode::getCode, search);
        }
        queryWrapper.lambda().orderByDesc(OauthCode::getCode);
        return oauthCodeService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询授权码详情", notes = "查询授权码详情", httpMethod = "GET")
    public OauthCode findById(@ApiParam(name = "id", value = "授权码id", required = true) @PathVariable("id") Integer id) {
        return oauthCodeService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改授权码", notes = "通过id修改授权码")
    public void updateById(OauthCode oauthCode) {
        oauthCodeService.updateById(oauthCode);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加授权码", notes = "添加授权码", response = IdVo.class)
    public Object save(

            @ApiParam(name = "code", value = "")
            @RequestParam(required = false, name = "code") String code
            ,
            @ApiParam(name = "authentication", value = "")
            @RequestParam(required = false, name = "authentication") String authentication
    ) {
        OauthCode oauthCode = new OauthCode(

                code, authentication);
        oauthCodeService.save(oauthCode);
        return oauthCode.getCode();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除授权码", notes = "修改授权码")
    public void deleteById(@ApiParam(name = "id", value = "授权码id", required = true) @PathVariable("id") Integer id) {
        oauthCodeService.removeById(id);
    }

}
