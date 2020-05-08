package moe.cnkirito.security.oauth2.code.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Description: 接口拦截
 *
 * @Author: 华仔
 * @Date: 2019/7/1
 */
@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        showMethodLog(request);
        return true;
    }

    /**
     * <p> 用来显示请求方法和请求参数的
     *
     * @param request HttpServletRequest
     */
    private void showMethodLog(HttpServletRequest request) {
        String requestUriString = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUriString.substring(contextPath.length());
        StringBuilder bfParams = new StringBuilder();
        if (url.contains("/null")) {
            return;
        }
        @SuppressWarnings("unchecked")
        Enumeration<String> paraNames = request.getParameterNames();
        String key;
        String value;
        while (paraNames.hasMoreElements()) {
            key = paraNames.nextElement();
            value = request.getParameter(key);
            bfParams.append(key).append("=").append(value).append("&");
        }
        if (bfParams.length() == 0) {
            bfParams.append(request.getQueryString());
        }

        String strMessage = String.format("========[方法]:%s,[请求]:%s,[参数]:%s", request.getMethod(), url, bfParams.toString());
        log.info(strMessage);
    }
}
