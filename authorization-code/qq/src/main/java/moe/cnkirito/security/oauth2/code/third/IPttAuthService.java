package moe.cnkirito.security.oauth2.code.third;

/**
 * @author huazai
 * @description pttAuth认证服务
 * @date 2020/5/26
 */
public interface IPttAuthService {
    /**
     * description: pttauth验证接口
     *
     * @param userId : 用户名称
     * @param token  : 令牌
     * @return : boolean true：成功，fales：失败
     */
    boolean auth(String userId, String token);
}
