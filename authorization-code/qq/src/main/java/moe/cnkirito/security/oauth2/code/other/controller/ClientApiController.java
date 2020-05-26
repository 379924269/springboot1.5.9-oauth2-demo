package moe.cnkirito.security.oauth2.code.other.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import moe.cnkirito.security.oauth2.code.module.entity.Users;
import moe.cnkirito.security.oauth2.code.module.service.IUsersService;
import moe.cnkirito.security.oauth2.code.vo.GetContactInfoVo;
import moe.cnkirito.security.oauth2.code.vo.GetUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Api(value = "ClientApiController", description = "手机APP接口")
@RestController
@RequestMapping(value = "client", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClientApiController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private JdbcTokenStore jdbcTokenStore;

    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有",
            notes = "因为username可能为userid、mobile和email字段所以我查询的时候是userid>mobile>email这种悠闲顺序查询的",
            response = GetUserInfoVo.class)
    public Object getUserInfo(HttpServletRequest request) {
        String token = request.getParameter("access_token");
        if (token == null) {
            token = request.getHeader("authorization").split(" ")[1];
        }
        OAuth2Authentication auth2Authentication = jdbcTokenStore.readAuthentication(token);
        String userName = auth2Authentication.getName();
        List<Users> userInfoList;
        List<Users> userInfoList1 = usersService.list(new QueryWrapper<Users>().lambda().eq(Users::getUserId, userName));
        List<Users> userInfoList2 = usersService.list(new QueryWrapper<Users>().lambda().eq(Users::getMobile, userName));
        List<Users> userInfoList3 = usersService.list(new QueryWrapper<Users>().lambda().eq(Users::getEmail, userName));

        if (!userInfoList1.isEmpty()) {
            userInfoList = userInfoList1;
        } else if (!userInfoList2.isEmpty()) {
            userInfoList = userInfoList2;
        } else {
            userInfoList = userInfoList3;
        }


        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("userId", userInfoList.get(0).getUserId());
        responseMap.put("username", userInfoList.get(0).getUsername());
        responseMap.put("nickname", userInfoList.get(0).getNickname());
        responseMap.put("email", userInfoList.get(0).getEmail());
        responseMap.put("mobile", userInfoList.get(0).getMobile());
        responseMap.put("avatar", userInfoList.get(0).getAvatar());

        return responseMap;
    }


    @RequestMapping(value = "getContactInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有联系人信息", notes = "查询所有联系人信息", response = GetContactInfoVo.class)
    public Object getContactInfo() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().gt(Users::getUserId, 10000);
        List<Users> dbUesrList = usersService.list(queryWrapper);
        List<GetContactInfoVo> responseList = new ArrayList<>();
        GetContactInfoVo getContactInfoVo = new GetContactInfoVo();

        for (Users u : dbUesrList) {
            getContactInfoVo.setUserId(u.getUserId());
            responseList.add(getContactInfoVo);
        }

        return responseList;
    }
}
