package com.onionknight.data4dota2.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author :fdy
 * @Date :Created in 15:19 2019/1/11
 * @Description:
 * @ModifiedBy :
 * @Version :
 */

public class CorConfig extends WebMvcConfigurationSupport{
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        System.out.println("success");
        registry.addMapping("/**")
                .allowedOrigins("*").
                allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true).maxAge(3600);
    }
}
