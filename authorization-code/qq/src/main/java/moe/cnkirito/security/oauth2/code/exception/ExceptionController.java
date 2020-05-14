package moe.cnkirito.security.oauth2.code.exception;

import moe.cnkirito.security.oauth2.code.tips.ErrorTip;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 自定义的 客户端验证错误
 *
 * @author: 华仔
 * @date: 2020/5/9
 */
@RestController
public class ExceptionController {

    /**
     * description: 自定义客户端id错误， 这个是自定义的跳转接口，status：401，也可以自定义。服务器返回的错误信息为html，如下：
     * <html><body><h1>OAuth Error</h1><p>error=&quot;invalid_request&quot;, error_description=&quot;Bad client
     * credentials&quot;</p></body></html>
     */
    @RequestMapping("/oauth/error")
    public Object error() {
        return new ErrorTip(401, "错误的客户端id");
    }
}
