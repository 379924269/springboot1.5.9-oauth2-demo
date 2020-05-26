package moe.cnkirito.security.oauth2.code;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import moe.cnkirito.security.oauth2.code.constant.Oauth2InitDBDataConst;
import moe.cnkirito.security.oauth2.code.module.entity.OauthClientDetails;
import moe.cnkirito.security.oauth2.code.module.service.IClientdetailsService;
import moe.cnkirito.security.oauth2.code.module.service.IOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author 徐靖峰[OF2938]
 *         company qianmi.com
 *         Date 2018-04-25
 */
@SpringBootApplication
public class QQApp extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(QQApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(QQApp.class, args);
    }

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Autowired
    IClientdetailsService clientdetailsService;

    @Autowired
    IOauthClientDetailsService iOauthClientDetailsService;

    @PostConstruct
    public void initOauth2DBTable() {
        String dbName = dbUrl.substring(dbUrl.lastIndexOf("/") + 1, dbUrl.indexOf("?"));
        boolean hasDBTable = clientdetailsService.hasDbTable(dbName, "oauth_client_details");
        if (!hasDBTable) {
            clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_ACCESS_TOKEN);
            clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_APPROVALS);
            clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_CLIENT_DETAILS);
            clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_CLIENT_TOKEN);
            clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_CODE);
            clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_REFRESH_TOKEN);
            clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.CLIENTDETAILS);
        }


        List<OauthClientDetails> list = iOauthClientDetailsService.list(new QueryWrapper<OauthClientDetails>().lambda().eq(OauthClientDetails::getClientId, "658658"));
        if (list.isEmpty()) {
            OauthClientDetails oauthClientDetails = new OauthClientDetails();
            oauthClientDetails.setClientId("658658");
            oauthClientDetails.setResourceIds("qq");
            oauthClientDetails.setClientSecret("8968548898");
            oauthClientDetails.setScope("get_user_info, get_fanslist");
            oauthClientDetails.setAuthorizedGrantTypes("password,authorization_code,client_credentials,refresh_token,implicit");
            oauthClientDetails.setWebServerRedirectUri("http://localhost:8082/redirt/responseAuthorizationCode");
            oauthClientDetails.setAuthorities("ROLE_CLIENT");
            oauthClientDetails.setAuthorities("false");
            oauthClientDetails.setAccessTokenValidity(60 * 60 * 24 * 365);
            oauthClientDetails.setRefreshTokenValidity(60 * 60 * 24 * 365);
            iOauthClientDetailsService.save(oauthClientDetails);
        }
    }

}
