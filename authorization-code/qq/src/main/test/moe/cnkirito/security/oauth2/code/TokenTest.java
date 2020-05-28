package moe.cnkirito.security.oauth2.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author huazai
 * @description token的各种测试
 * @date 2020/4/29
 */
@Slf4j
public class TokenTest {
    @Test
    public void getInfoWithTokne() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        headers.set("Authorization", "bearer 9fa671c0-fb94-4f32-af42-79a9d6e9b699");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth2/client/getContactInfo", requestEntity, String.class);
        String getAuthorizeCode = response.getBody();
        log.info("getAuthorizeCode => {}",getAuthorizeCode);
    }

    @Test
    public void passwordModeThirdTest() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("username", "10680");
        params.add("password", "d8ef0d7f8c17e2cb2b3bfd958e79c5dae2fc964fbfb36b9c6e283991e7b12a20");
        params.add("grant_type", "password");
        params.add("client_id", "658658");
        params.add("client_secret", "8968548898");
        params.add("third", "10680");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, null);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth2/oauth/token", requestEntity, String.class);
            Integer statusCode = response.getStatusCodeValue();
            System.out.println("statusCode = " + statusCode);

            String responseBody = response.getBody();

            log.info("responseBody => {}",responseBody);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void passwordModeTest() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("username", "dnp1@delinp.cn");
        params.add("password", "@dnp1");
        params.add("grant_type", "password");
        params.add("client_id", "658658");
        params.add("client_secret", "8968548898");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, null);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://192.168.0.201:8001/oauth2/oauth/token", requestEntity, String.class);
            Integer statusCode = response.getStatusCodeValue();
            System.out.println("statusCode = " + statusCode);

            String responseBody = response.getBody();

            log.info("responseBody => {}",responseBody);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }
}
