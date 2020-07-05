package com.hbzf.draw.config;

import com.hbzf.draw.common.UserSessionInterceptor;
import com.hbzf.draw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  /*  @Autowired
    private UserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new UserSessionInterceptor(userService)).addPathPatterns("/");
    }*/

}