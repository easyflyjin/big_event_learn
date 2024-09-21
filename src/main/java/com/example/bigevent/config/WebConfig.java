package com.example.bigevent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.bigevent.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Value("${file.upload}")
    private String fileUpload;

    @Value("${file.path}")
    private String filePath;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register","/file/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUpload).addResourceLocations("file:///"+filePath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
