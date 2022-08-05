package com.example.Iamport_example.Service;

import com.example.Iamport_example.Dto.Iamport.PaymentDto;
import com.example.Iamport_example.Dto.Iamport.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PayService {
    @Value("${iamport.rest.key}")
    private String IMP_KEY;
    @Value("${iamport.rest.secret}")
    private String IMP_SECRET;

    public String getIamportAccessToken(){
        if (IMP_KEY == null || IMP_SECRET == null)
            throw new NullPointerException("iamport key or secret is null");

        //RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        // 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 바디
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("imp_key", IMP_KEY);
        parameters.add("imp_secret", IMP_SECRET);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(parameters, headers);

        // REST API 호출
        String baseUrl = "https://api.iamport.kr/users/getToken";

        TokenDto accessToken = restTemplate.postForObject(baseUrl, body, TokenDto.class);

        if (Objects.requireNonNull(accessToken).getCode() == 0){
            return accessToken.getResponse().getAccess_token();
        }
        return null;
    }

    public PaymentDto getIamportPaymentData(String imp_uid, String accessToken){
        if (imp_uid == null || accessToken == null)
            return null;

        //RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        // 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(null, headers);
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        // REST API 호출
        String baseUrl = String.format("https://api.iamport.kr/payments/%s", imp_uid);
        return restTemplate.postForObject(baseUrl, entity, PaymentDto.class);
    }

}
