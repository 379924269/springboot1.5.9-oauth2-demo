package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.Role;
import moe.cnkirito.security.oauth2.code.module.service.IRoleService;
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
@Api(value = "RoleController", description = "")
@RestController
@RequestMapping(value = "/module/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<Role> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(Role::getId, search);
        }
        queryWrapper.lambda().orderByDesc(Role::getId);
        return roleService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET")
    public Role findById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        return roleService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改", notes = "通过id修改")
    public void updateById(Role role) {
        roleService.updateById(role);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "添加", response = IdVo.class)
    public Object save(

            @ApiParam(name = "name", value = "")
            @RequestParam(required = false, name = "name") String name
    ) {
        Role role = new Role(

                name);
        roleService.save(role);
        return role.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除", notes = "修改")
    public void deleteById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        roleService.removeById(id);
    }

}
