package com.example.springboottutorial.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class IdTokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // セッションからidTokenを取得
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = (HttpSession) httpServletRequest.getSession();
        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        OidcUser user = (OidcUser) context.getAuthentication().getPrincipal();
        String token = user.getIdToken().getTokenValue();

        if (token != null && !token.isEmpty()) {
            // idTokenをスレッドローカルに保持
            RequestContext.getContext().setToken(token);
        }

        chain.doFilter(request, response);

    }

}