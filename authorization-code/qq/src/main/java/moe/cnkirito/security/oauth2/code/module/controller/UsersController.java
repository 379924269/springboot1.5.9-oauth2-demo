package moe.cnkirito.security.oauth2.code.module.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.cnkirito.security.oauth2.code.module.entity.Users;
import moe.cnkirito.security.oauth2.code.module.service.IUsersService;
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
 * @since 2020-05-14
 */
@Api(value = "UsersController", description = "")
@RestController
@RequestMapping(value = "/module/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public Object findAll(PageVo pageVo,
                          @ApiParam(name = "search", value = "模糊查询字段") @RequestParam(required = false, defaultValue = "") String search) {
        Page<Users> page = new Page(pageVo.getOffset(), pageVo.getLimit());
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.lambda().like(Users::getUserId, search);
        }
        queryWrapper.lambda().orderByDesc(Users::getUserId);
        return usersService.page(page, queryWrapper);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET")
    public Users findById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        return usersService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "通过id修改", notes = "通过id修改")
    public void updateById(Users users) {
        usersService.updateById(users);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "添加", response = IdVo.class)
    public Object save(

            @ApiParam(name = "userid", value = "代理id")
            @RequestParam(required = false, name = "userid") Integer userid
            ,
            @ApiParam(name = "username", value = "用户名称")
            @RequestParam(required = false, name = "username") String username
            ,
            @ApiParam(name = "nickname", value = "昵称")
            @RequestParam(required = false, name = "nickname") String nickname
            ,
            @ApiParam(name = "employeeid", value = "工号")
            @RequestParam(required = false, name = "employeeid") String employeeid
            ,
            @ApiParam(name = "avatar", value = "头像（头像地址）")
            @RequestParam(required = false, name = "avatar") String avatar
            ,
            @ApiParam(name = "secret", value = "密码MD5，保留兼容语音通话")
            @RequestParam(required = false, name = "secret") String secret
            ,
            @ApiParam(name = "password", value = "SHA256计算的密码hash值")
            @RequestParam(required = false, name = "password") String password
            ,
            @ApiParam(name = "defaultchannel", value = "默认频道")
            @RequestParam(required = false, name = "defaultchannel") Integer defaultchannel
            ,
            @ApiParam(name = "enable", value = "0 禁用 1 启用 2 到期")
            @RequestParam(required = false, name = "enable") Integer enable
            ,
            @ApiParam(name = "type", value = "用户类型，“1”表示记时注册用户，“2表示终身用户”")
            @RequestParam(required = false, name = "type") Integer type
            ,
            @ApiParam(name = "createid", value = "创建者（管理员）的ID")
            @RequestParam(required = false, name = "createid") Integer createid
            ,
            @ApiParam(name = "maturitytime", value = "用户到期时间")
            @RequestParam(required = false, name = "maturitytime") LocalDateTime maturitytime
            ,
            @ApiParam(name = "imeiid", value = "用户手机串号ID外键")
            @RequestParam(required = false, name = "imeiid") Integer imeiid
            ,
            @ApiParam(name = "lastlogintime", value = "上次登录时间")
            @RequestParam(required = false, name = "lastlogintime") LocalDateTime lastlogintime
            ,
            @ApiParam(name = "email", value = "邮箱地址，和emm一致，可以用来登录")
            @RequestParam(required = false, name = "email") String email
            ,
            @ApiParam(name = "uuid", value = "用户标志，目前用来作为客户端本地数据库的密码")
            @RequestParam(required = false, name = "uuid") String uuid
            ,
            @ApiParam(name = "pubkey", value = "公钥")
            @RequestParam(required = false, name = "pubkey") String pubkey
            ,
            @ApiParam(name = "sdcardid", value = "加密卡")
            @RequestParam(required = false, name = "sdcardid") Integer sdcardid
            ,
            @ApiParam(name = "mobile", value = "手机号码,可以用来登录")
            @RequestParam(required = false, name = "mobile") String mobile
            ,
            @ApiParam(name = "signInfo", value = "个性签名")
            @RequestParam(required = false, name = "signInfo") String signInfo
            ,
            @ApiParam(name = "gender", value = "1男2女0未知")
            @RequestParam(required = false, name = "gender") Boolean gender
            ,
            @ApiParam(name = "departId", value = "所属部门Id")
            @RequestParam(required = false, name = "departId") Integer departId
            ,
            @ApiParam(name = "created", value = "创建时间，单位秒")
            @RequestParam(required = false, name = "created") Integer created
            ,
            @ApiParam(name = "updated", value = "更新时间，单位秒")
            @RequestParam(required = false, name = "updated") Integer updated
            ,
            @ApiParam(name = "verificationcode", value = "验证码")
            @RequestParam(required = false, name = "verificationcode") String verificationcode
            ,
            @ApiParam(name = "loginerrorcount", value = "登录错误次数")
            @RequestParam(required = false, name = "loginerrorcount") Integer loginerrorcount
            ,
            @ApiParam(name = "codeexpirationtime", value = "验证码过期时间")
            @RequestParam(required = false, name = "codeexpirationtime") Long codeexpirationtime
    ) {
        Users users = new Users(

                userid, username, nickname, employeeid, avatar, secret, password, defaultchannel, enable, type, createid, maturitytime, imeiid, lastlogintime, email, uuid, pubkey, sdcardid, mobile, signInfo, gender, departId, created, updated, verificationcode, loginerrorcount, codeexpirationtime);
        usersService.save(users);
        return users.getUserId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除", notes = "修改")
    public void deleteById(@ApiParam(name = "id", value = "id", required = true) @PathVariable("id") Integer id) {
        usersService.removeById(id);
    }

}
