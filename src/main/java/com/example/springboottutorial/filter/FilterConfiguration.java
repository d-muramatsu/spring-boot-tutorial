package com.example.springboottutorial.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean getPeticionFilter() {

        // IdTokenFilterをFilterChainに登録
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new IdTokenFilter());
        registration.addUrlPatterns("/registered/*");
        registration.setName("requestFilter");

        return registration;
    }
}