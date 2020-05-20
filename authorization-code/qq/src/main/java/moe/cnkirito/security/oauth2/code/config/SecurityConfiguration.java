package moe.cnkirito.security.oauth2.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
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
        auth.userDetailsService(customUserDetailsServiceConfig).passwordEncoder(passwordEncoder());
    }
}
