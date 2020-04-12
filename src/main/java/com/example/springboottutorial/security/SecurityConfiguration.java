package com.example.springboottutorial.security;

import java.util.ArrayList;
import java.util.List;

import com.example.springboottutorial.domain.model.User;
import com.example.springboottutorial.domain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/");

        // @formatter:off
        http.authorizeRequests()
            .antMatchers("/", "/error", "/webjars/**").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/registered/**").hasRole("REGISTERED_USER")   // 登録済みユーザーのみアクセスできるパスを指定
            .anyRequest().authenticated();

        http.exceptionHandling()
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        http.logout()
            .logoutSuccessUrl("/").permitAll();

        http.csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        http.oauth2Login()  // ソーシャルログインを有効化する
            .failureHandler((request, response, exception) -> {
                request.getSession().setAttribute("error.message", exception.getMessage());
                handler.onAuthenticationFailure(request, response, exception);
            })
            .userInfoEndpoint()
            .userAuthoritiesMapper(this.oauth2UserAuthoritiesMapper());
		// @formatter:on
    }

    private GrantedAuthoritiesMapper oauth2UserAuthoritiesMapper() {
        // インタフェース的には複数件受け取ることができるが、実際には権限情報(ROLE_USER)の１件のみが渡される
        return authorities -> {
            // System.out.println("SecurityConfiguration#oauth2UserAuthoritiesMapper");
            List<GrantedAuthority> mappedAuthorities = new ArrayList<>();
            for (GrantedAuthority authority : authorities) {
                // オリジナルの権限情報は引き継ぐ
                mappedAuthorities.add(authority);
                if (OAuth2UserAuthority.class.isInstance(authority)) {
                    // OAuth 2.0 Login機能でログインしたユーザに与える権限情報(ROLE_OAUTH_USER)を追加
                    mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_OAUTH_USER"));
                    OAuth2UserAuthority oauth2UserAuthority = OAuth2UserAuthority.class.cast(authority);
                    // 登録済みユーザーか(DBにレコードが存在するか)検証
                    String email = (String) oauth2UserAuthority.getAttributes().get("email");
                    User registeredUser = userService.getUser(email);
                    if (registeredUser != null) {
                        // 登録済みユーザーに与える権限を追加
                        // System.out.println("登録済みユーザーです");
                        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_REGISTERED_USER"));
                    }
                }
            }
            return mappedAuthorities;
        };
    }
}