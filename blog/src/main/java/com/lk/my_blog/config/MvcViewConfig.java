package com.lk.my_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: 刘康
 * @Date: 2021/7/22 9:56
 * @Description:
 */
@Configuration
public class MvcViewConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver myLocaleResolver(){
        return new MyLocalResolveConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
