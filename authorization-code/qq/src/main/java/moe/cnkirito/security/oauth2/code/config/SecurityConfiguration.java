package moe.cnkirito.security.oauth2.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // 创建两个 qq 用户
        manager.createUser(User.withUsername("admin").password("123456").authorities("USER").build());
        manager.createUser(User.withUsername("admin1").password("123456").authorities("USER").build());
        return manager;
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    参考地址：https://blog.csdn.net/lixiang987654321/article/details/88132204
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      // 任何人(包括没有经过验证的)都可以访问”/login”和”/login?error”
        http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();

        http.csrf().disable();

        http.authorizeRequests().antMatchers("/systemClientdetailsController/listClientDetails",
                "/module/JdbcTokenStroeController")
                .permitAll();

//       不验证swagger 接口
        http.authorizeRequests().regexMatchers(".*swagger.*", ".*v2.*", ".*webjars.*").permitAll()
        .anyRequest().authenticated();


    }
}
