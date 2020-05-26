package moe.cnkirito.security.oauth2.code.third;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author huazai
 * @description
 * @date 2020/5/26
 */
@Slf4j
@Service
public class PttAuthServiceImpl implements IPttAuthService {

    @Value("${ptt-auth-server-url}")
    private String pttAuthUrl;

    @Override
    public boolean auth(String userId, String token) {
        String url = pttAuthUrl + "/pttauth/auth";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("user", userId);
        requestParams.add("token", token);
        requestParams.add("method", "verify");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestParams, String.class);
        Integer status = responseEntity.getStatusCode().value();

        String response = responseEntity.getBody();
        if (status == 200) {
            if (response.contains("200")) {
                return true;
            } else {
                return false;
            }
        } else {
            log.error("=============================请求异常，异常码：{}", status);
            return false;
        }
    }
}
