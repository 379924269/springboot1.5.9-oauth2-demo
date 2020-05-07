package moe.cnkirito.security.oauth2.code.exception.token.resource;

import moe.cnkirito.security.oauth2.code.util.HttpUtilsResultVO;
import moe.cnkirito.security.oauth2.code.util.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 无效Token返回处理器 参考地址：https://github.com/weizongwu/SpringCloud-OAuth2-SpringSecurity-Frame项目
 * description:
 * @author: 华仔
 * @date: 2020/5/7
 */
@Component
public class MyTokenExceptionEntryPoint implements AuthenticationEntryPoint {
    /**
     * Commences an authentication scheme.
     * <p>
     * <code>ExceptionTranslationFilter</code> will populate the <code>HttpSession</code>
     * attribute named
     * <code>AbstractAuthenticationProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY</code>
     * with the requested target URL before calling this method.
     * <p>
     * Implementations should modify the headers on the <code>ServletResponse</code> as
     * necessary to commence the authentication process.
     *
     * @param request       that resulted in an <code>AuthenticationException</code>
     * @param response      so that the user agent can begin authentication
     * @param authException that caused the invocation
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        Throwable cause = authException.getCause();  如果需要具体判断类型的话，从这里取类型
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            HttpUtilsResultVO.writerError(new ResponseVo(401, authException.getMessage()), response);
//               response.getWriter().write(new ResponseVo(401, authException.getMessage()), response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}