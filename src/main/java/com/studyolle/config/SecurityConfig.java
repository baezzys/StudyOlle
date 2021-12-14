package com.studyolle.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    /*
    기본적으로 스프링에서는 CSRF가 활성화 되어 있다. CSRF토큰이라는 것을 사용하여 타사이트에서 요청보내는 것을 막는다.
    타임리프같은 폼으로 만들었을때 타임리프에서 CSRF토큰 값이 자동으로 설정되어 있다.
    그런데 CSRF토큰 없이 폼데이터가 온다면 403 에러가 나오게 된다.
    그래서 테스트 코드에서 에러가 나온다.


     */
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/sign-up", "/check-email-token",
                        "/email-login", "/login-by-email", "/search/study").permitAll()
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
