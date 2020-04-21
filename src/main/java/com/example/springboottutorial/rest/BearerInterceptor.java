package com.example.springboottutorial.rest;

import java.io.IOException;

import com.example.springboottutorial.filter.RequestContext;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * RestTemplate利用時、AuthorizationヘッダーとしてBearer情報を付与するインタセプター
 */
@Configuration
public class BearerInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        request.getHeaders().add(RequestContext.REQUEST_HEADER_NAME,
                "Bearer " + RequestContext.getContext().getToken());

        return execution.execute(request, body);
    }

}