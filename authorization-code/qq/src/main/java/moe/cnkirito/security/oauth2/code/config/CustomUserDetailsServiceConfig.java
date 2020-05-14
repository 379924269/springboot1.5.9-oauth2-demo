package moe.cnkirito.security.oauth2.code.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.cnkirito.security.oauth2.code.module.entity.User;
import moe.cnkirito.security.oauth2.code.module.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomUserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    private IUserService iUserService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = iUserService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, userName));
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("user");
        authorities.add(authority);

//明天来改一下这里， 参考https://blog.csdn.net/I_am_Hutengfei/article/details/100561564 的userdetail
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
