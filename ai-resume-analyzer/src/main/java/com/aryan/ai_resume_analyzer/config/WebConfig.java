package com.aryan.ai_resume_analyzer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
       // WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Allow your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these actions
                .allowedHeaders("*"); // Allow all headers
    }
}
