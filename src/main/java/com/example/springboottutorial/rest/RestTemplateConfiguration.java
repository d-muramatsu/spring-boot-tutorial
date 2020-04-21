package com.example.springboottutorial.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        // RestTemplate利用時、AuthorizationヘッダーとしてBearer情報を付与するインタセプターを適用
        return new RestTemplateBuilder().additionalInterceptors(new BearerInterceptor());
    }

}