package com.icia.springtest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    SessionInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                //모든 경로의 url 인터셉터
                .addPathPatterns("/**") //member/join/1
                //인터셉터 제외할 url
                .excludePathPatterns("/", "/user/login", "/user/logout")
                .excludePathPatterns("/user/join")
                .excludePathPatterns("/js/**", "/css/**", "/img/**")
                .excludePathPatterns("/favicon.ico", "/error");

    }
}