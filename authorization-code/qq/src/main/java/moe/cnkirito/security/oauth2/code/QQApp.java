package moe.cnkirito.security.oauth2.code;

import moe.cnkirito.security.oauth2.code.constant.Oauth2InitDBDataConst;
import moe.cnkirito.security.oauth2.code.module.service.IClientdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author 徐靖峰[OF2938]
 * company qianmi.com
 * Date 2018-04-25
 */
@SpringBootApplication
public class QQApp {

    public static void main(String[] args) {
        SpringApplication.run(QQApp.class, args);
    }

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Autowired
    IClientdetailsService clientdetailsService;

    @PostConstruct
    public void initOauth2DBTable(){
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
    }

}
