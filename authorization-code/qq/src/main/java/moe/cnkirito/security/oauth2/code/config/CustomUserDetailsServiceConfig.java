package moe.cnkirito.security.oauth2.code.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.cnkirito.security.oauth2.code.module.entity.Users;
import moe.cnkirito.security.oauth2.code.module.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * description: 实现自定义的账号和密码
 *
 * @author: 华仔
 * @date: 2020/5/20
 */
@Component
public class CustomUserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    private IUsersService iUsersService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = iUsersService.getOne(new QueryWrapper<Users>().lambda().eq(Users::getMobile, userName));
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("user");
        authorities.add(authority);

        //参考https://blog.csdn.net/I_am_Hutengfei/article/details/100561564 的userdetail
        return new org.springframework.security.core.userdetails.User(user.getMobile(), user.getPassword(), authorities);
    }
}
