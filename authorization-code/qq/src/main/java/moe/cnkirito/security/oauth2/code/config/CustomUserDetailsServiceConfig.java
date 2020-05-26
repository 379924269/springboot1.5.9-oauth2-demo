package moe.cnkirito.security.oauth2.code.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.cnkirito.security.oauth2.code.module.entity.Users;
import moe.cnkirito.security.oauth2.code.module.service.IUsersService;
import moe.cnkirito.security.oauth2.code.third.IPttAuthService;
import moe.cnkirito.security.oauth2.code.util.EncryptPasswordUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

/**
 * description: 实现自定义的账号和密码（有2中验证）
 * 1、手机app用userid和token（pttauth上的token）
 * 2、正常的验证知识用户字段可以过个验证
 *
 * @author: 华仔
 * @date: 2020/5/20
 */
@Component
public class CustomUserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    IPttAuthService pttAuthService;

    @Autowired
    private IUsersService iUsersService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // 1、验证直接用userid和pttauth的token验证
        if (StringUtils.isNotEmpty(request.getParameter("third"))) {
            boolean validToken = pttAuthService.auth(userName, request.getParameter("password"));
            if (validToken) {
                try {
                    String encrptyPassword = DigestUtils.md5Hex(EncryptPasswordUtil.encryptPassword(request.getParameter("password")));
                    return new org.springframework.security.core.userdetails.User(userName, encrptyPassword, authorities);
                } catch (Exception e) {
                    throw new UsernameNotFoundException("token 验证错误");
                }
            } else {
                throw new UsernameNotFoundException("token 验证错误");
            }
        }

        // 2、两种账号登录方式：手机号码、邮箱，手机号码优先
        String dbUsername;
        Users user = iUsersService.getOne(new QueryWrapper<Users>().lambda().eq(Users::getMobile, userName));

        if (user == null) {
            user = iUsersService.getOne(new QueryWrapper<Users>().lambda().eq(Users::getEmail, userName));
            if (user == null) {
                throw new UsernameNotFoundException("UserName " + userName + " not found");
            }
            dbUsername = user.getEmail();
        } else {
            dbUsername = user.getMobile();
        }


        //参考https://blog.csdn.net/I_am_Hutengfei/article/details/100561564 的userdetail
        return new org.springframework.security.core.userdetails.User(dbUsername, user.getPassword(), authorities);
    }
}
