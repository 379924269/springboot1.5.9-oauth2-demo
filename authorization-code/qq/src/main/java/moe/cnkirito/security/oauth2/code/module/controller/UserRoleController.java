package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.UserRole;
import moe.cnkirito.security.oauth2.code.module.service.IUserRoleService;
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
@Api(value = "UserRoleController", description = "")
@RestController
@RequestMapping(value = "/module/userRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserRoleController {
    @Autowired
    private IUserRoleService userRoleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<UserRole> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(UserRole::getUserId, search);
        }
        queryWrapper.lambda().orderByDesc(UserRole::getRoleId);
        return userRoleService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET")
    public UserRole findById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        return userRoleService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改", notes = "通过id修改")
    public void updateById(UserRole userRole) {
        userRoleService.updateById(userRole);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "添加", response = IdVo.class)
    public Object save(

            @ApiParam(name = "userId", value = "")
            @RequestParam(required = false, name = "userId") Long userId
            ,
            @ApiParam(name = "roleId", value = "")
            @RequestParam(required = false, name = "roleId") Long roleId
    ) {
        UserRole userRole = new UserRole(

                userId, roleId);
        userRoleService.save(userRole);
        return userRole.getRoleId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除", notes = "修改")
    public void deleteById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        userRoleService.removeById(id);
    }

}
