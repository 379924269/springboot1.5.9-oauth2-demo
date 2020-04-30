//package moe.cnkirito.security.oauth2.code.endpoint;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import moe.cnkirito.security.oauth2.code.module.entity.Clientdetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <p>
// * jdbcClientDetailsService控制器
// * </p>
// *
// * @author huazai
// * @since 2020-04-29
// */
//@Api(value = "SystemClientdetailsController", description = "")
//@RestController
//@RequestMapping(value = "/systemClientdetailsController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//public class SystemClientdetailsController {
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//
//    @RequestMapping(value = "/listClientDetails", method = RequestMethod.GET)
//    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET")
//    public Object listClientDetails() {
//        return clientDetailsService.loadClientByClientId()
//    }
//
//}
