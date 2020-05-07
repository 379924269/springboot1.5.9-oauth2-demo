/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package moe.cnkirito.security.oauth2.code.config;

import moe.cnkirito.security.oauth2.code.exception.token.resource.MyTokenExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Rob Winch
 */
@Configuration
public class OAuth2ServerConfig {

    private static final String QQ_RESOURCE_ID = "qq";

    @Configuration
    @EnableResourceServer()
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Resource
        private MyTokenExceptionEntryPoint tokenExceptionEntryPoint; // token失效处理器

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(QQ_RESOURCE_ID).stateless(true);
//            resources.authenticationEntryPoint(tokenExceptionEntryPoint); // token失效处理器
            // 如果关闭 stateless，则 accessToken 使用时的 session id 会被记录，后续请求不携带 accessToken 也可以正常响应
//            resources.resourceId(QQ_RESOURCE_ID).stateless(false);
        }


//        TODO 自定义登录界面和授权界面https://blog.csdn.net/baidu_34389984/article/details/85269366
        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http

//                .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                    .and()
                .requestMatchers()
                    // 保险起见，防止被主过滤器链路拦截
                    .antMatchers("/qq/**").and()
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                .authorizeRequests()
                    .antMatchers("/qq/info/**").access("#oauth2.hasScope('get_user_info')")
                    .antMatchers("/qq/fans/**").access("#oauth2.hasScope('get_fanslist')");
            // @formatter:on
        }

    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Autowired
        private WebResponseExceptionTranslator webResponseExceptionTranslator;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // @formatter:off
//            clients.inMemory().withClient("aiqiyi")
//                    .resourceIds(QQ_RESOURCE_ID)
//                    .authorizedGrantTypes("password","authorization_code","client_credentials", "refresh_token", "implicit")
//                    .authorities("ROLE_CLIENT")
//                    .scopes("get_user_info", "get_fanslist")
//                    .secret("secret")
//                    .redirectUris("http://localhost:8081/aiqiyi/qq/redirect")
////                    .autoApprove(true)
////                    .autoApprove("get_user_info")
//                    .and()
//                    .withClient("youku")
//                    .resourceIds(QQ_RESOURCE_ID)
//                    .authorizedGrantTypes("password","authorization_code","client_credentials", "refresh_token", "implicit")
//                    .authorities("ROLE_CLIENT")
//                    .scopes("get_user_info", "get_fanslist")
//                    .secret("secret")
//                    .redirectUris("http://localhost:8082/youku/qq/redirect");


            // @formatter:on
            clients.withClientDetails(clientDetails());
        }

        @Bean
        public ApprovalStore approvalStore() {
            TokenApprovalStore store = new TokenApprovalStore();
            store.setTokenStore(tokenStore());
            return store;
        }

        @Bean
        public ClientDetailsService clientDetails() {
            return new JdbcClientDetailsService(dataSource);
        }


        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

//        UserDetailsService配置的原因：参考：https://www.jianshu.com/p/c8e639f86744
        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints.tokenStore(tokenStore())
                    .authenticationManager(authenticationManager)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
            endpoints.userDetailsService(userDetailsService);

            endpoints.pathMapping("/oauth/confirm_access","/custom/confirm_access");

            endpoints.exceptionTranslator(webResponseExceptionTranslator);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            oauthServer.realm(QQ_RESOURCE_ID).allowFormAuthenticationForClients();
            oauthServer.checkTokenAccess("permitAll()");
        }

    }


}
