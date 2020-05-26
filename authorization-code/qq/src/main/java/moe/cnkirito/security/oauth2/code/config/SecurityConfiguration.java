package moe.cnkirito.security.oauth2.code.config;

import moe.cnkirito.security.oauth2.code.util.EncryptPasswordUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsServiceConfig customUserDetailsServiceConfig;

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return customUserDetailsServiceConfig;
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // secureity无状态参考地址：https://blog.csdn.net/u010365540/article/details/81842787
    // 参考地址：https://blog.csdn.net/lixiang987654321/article/details/88132204
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 任何人(包括没有经过验证的)都可以访问”/login”和”/login?error”
        http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();

        http.csrf().disable();

        http.authorizeRequests().antMatchers("/systemClientdetailsController/listClientDetails",
                "/module/JdbcTokenStroeController", "http://localhost:8082/redirt/responseAuthorizationCode"
                , "/redirt/listClientDetails", "/assets/**", "/doc")
                .permitAll();

        //不验证swagger 接口
        http.authorizeRequests().regexMatchers(".*swagger.*", ".*v2.*", ".*webjars.*").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsServiceConfig).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5Hex((String) charSequence);
            }

            // 这个地方  我参考别人实现了但是上面的encode方法没有调用不知到问什么，值嗲用了下面的matches方法
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                try {
                    return s.equals(DigestUtils.md5Hex(EncryptPasswordUtil.encryptPassword((String) charSequence)));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }
}
