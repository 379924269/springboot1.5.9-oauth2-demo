package moe.cnkirito.security.oauth2.code.serviceTest;

import moe.cnkirito.security.oauth2.code.constant.Oauth2InitDBDataConst;
import moe.cnkirito.security.oauth2.code.module.service.IClientdetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huazai
 * @description
 * @date 2020/5/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomUrlServiceImplTest {

    @Autowired
    private IClientdetailsService clientdetailsService;

//    @Test
//    public void findDBTable() {
//       boolean hasTabel =  clientdetailsService.hasDbTable("spring-cloud-auth", "oauth_refresh_token");
//        Assert.assertTrue(hasTabel);
//    }

    @Test
    public void addOauth2dDBTable() {
//        clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_ACCESS_TOKEN);
//        clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_APPROVALS);
//        clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_CLIENT_DETAILS);
//        clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_CLIENT_TOKEN);
//        clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_CODE);
//        clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.OAUTH_REFRESH_TOKEN);
//        clientdetailsService.addOauth2Table(Oauth2InitDBDataConst.CLIENTDETAILS);
    }
}
