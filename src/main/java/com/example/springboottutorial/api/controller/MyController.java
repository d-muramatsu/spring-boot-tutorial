package com.example.springboottutorial.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/registered/hello")
public class MyController {

    private RestTemplate restTemplate;

    @Value("${hello.be.api.url}")
    private URI url;

    public MyController(RestTemplateBuilder builder) {
        // コンストラクタインジェクション
        this.restTemplate = builder.build();
    }

    @GetMapping
    public String get() {
        // BEのAPI呼び出し
        restTemplate.getForEntity(url, String.class);

        return "Hello!";
    }
}