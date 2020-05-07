package moe.cnkirito.security.oauth2.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author huazai
 * @description token的各种测试
 * @date 2020/4/29
 */
@Slf4j
public class TokenTest {
    //        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
//        params.add("grant_type","authorization_code");
//        params.add("code",code);
//        params.add("client_id","aiqiyi");
//        params.add("client_secret","secret");
//        params.add("redirect_uri","https://www.baidu.com/");
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth/token", requestEntity, String.class);
//        String token = response.getBody();
//        log.info("token => {}",token);
//        return token;
    /**
     * description: 获取授权码， 注意
     * 1、重定向地址必须和注册的重定向地址一样，不然会报错
     * @author: 华仔
     * @date: 2020/4/29
     */
    @Test
    public void getAuthorizeCode() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        headers.set("Authorization", "13c59850-d1e4-4b41-9c69-87a357c698e9");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/qq/info/250577914", requestEntity, String.class);
        String getAuthorizeCode = response.getBody();
        log.info("getAuthorizeCode => {}",getAuthorizeCode);
    }

    @Test
    public void listTest() {
        String[] strArray = {"1", "2", "3"};
        List<String> list = Arrays.asList(strArray);
        list.add(3,"aa");
    }
}
