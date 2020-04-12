package com.example.springboottutorial.api.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registered/hello")
public class MyController {

    @GetMapping
    public String get(@AuthenticationPrincipal OAuth2User userDetails) {
        return "Hello!";
    }
}