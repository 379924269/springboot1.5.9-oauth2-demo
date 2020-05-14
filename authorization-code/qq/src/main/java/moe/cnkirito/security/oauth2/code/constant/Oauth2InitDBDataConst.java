package moe.cnkirito.security.oauth2.code.constant;

/**
 * @author huazai
 * @description Oauth2原始数据库
 * @date 2020/5/14
 */
public class Oauth2InitDBDataConst {
    public static final String CLIENTDETAILS = "CREATE TABLE IF NOT EXISTS `clientdetails` (\n" +
            "  `appId` varchar(128) NOT NULL,\n" +
            "  `resourceIds` varchar(256) DEFAULT NULL,\n" +
            "  `appSecret` varchar(256) DEFAULT NULL,\n" +
            "  `scope` varchar(256) DEFAULT NULL,\n" +
            "  `grantTypes` varchar(256) DEFAULT NULL,\n" +
            "  `redirectUrl` varchar(256) DEFAULT NULL,\n" +
            "  `authorities` varchar(256) DEFAULT NULL,\n" +
            "  `access_token_validity` int(11) DEFAULT NULL,\n" +
            "  `refresh_token_validity` int(11) DEFAULT NULL,\n" +
            "  `additionalInformation` varchar(4096) DEFAULT NULL,\n" +
            "  `autoApproveScopes` varchar(256) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`appId`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    public static final String OAUTH_ACCESS_TOKEN = "CREATE TABLE IF NOT EXISTS `oauth_access_token` (\n" +
            "  `token_id` varchar(256) DEFAULT NULL,\n" +
            "  `token` blob,\n" +
            "  `authentication_id` varchar(128) NOT NULL,\n" +
            "  `user_name` varchar(256) DEFAULT NULL,\n" +
            "  `client_id` varchar(256) DEFAULT NULL,\n" +
            "  `authentication` blob,\n" +
            "  `refresh_token` varchar(256) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`authentication_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成的token';";
    public static final String OAUTH_APPROVALS = "CREATE TABLE IF NOT EXISTS `oauth_approvals` (\n" +
            "  `userId` varchar(256) DEFAULT NULL,\n" +
            "  `clientId` varchar(256) DEFAULT NULL,\n" +
            "  `scope` varchar(256) DEFAULT NULL,\n" +
            "  `status` varchar(10) DEFAULT NULL,\n" +
            "  `expiresAt` datetime DEFAULT NULL,\n" +
            "  `lastModifiedAt` datetime DEFAULT NULL\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    public static final String OAUTH_CLIENT_DETAILS = "CREATE TABLE IF NOT EXISTS `oauth_client_details` (\n" +
            "  `client_id` varchar(255) NOT NULL,\n" +
            "  `resource_ids` varchar(256) DEFAULT NULL,\n" +
            "  `client_secret` varchar(256) DEFAULT NULL,\n" +
            "  `scope` varchar(256) DEFAULT NULL,\n" +
            "  `authorized_grant_types` varchar(256) DEFAULT NULL,\n" +
            "  `web_server_redirect_uri` varchar(256) DEFAULT NULL,\n" +
            "  `authorities` varchar(256) DEFAULT NULL,\n" +
            "  `access_token_validity` int(11) DEFAULT NULL,\n" +
            "  `refresh_token_validity` int(11) DEFAULT NULL,\n" +
            "  `additional_information` varchar(4096) DEFAULT NULL,\n" +
            "  `autoapprove` varchar(256) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`client_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 客户端信息';";
    public static final String OAUTH_CLIENT_TOKEN = "CREATE TABLE IF NOT EXISTS `oauth_client_token` (\n" +
            "  `token_id` varchar(256) DEFAULT NULL,\n" +
            "  `token` blob,\n" +
            "  `authentication_id` varchar(128) NOT NULL,\n" +
            "  `user_name` varchar(256) DEFAULT NULL,\n" +
            "  `client_id` varchar(256) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`authentication_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    public static final String OAUTH_CODE = "CREATE TABLE IF NOT EXISTS `oauth_code` (\n" +
            "  `code` varchar(256) DEFAULT NULL,\n" +
            "  `authentication` blob\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授权码';";
    public static final String OAUTH_REFRESH_TOKEN = "CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (\n" +
            "  `token_id` varchar(256) DEFAULT NULL,\n" +
            "  `token` blob,\n" +
            "  `authentication` blob\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='刷新token';";
}
