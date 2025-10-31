package com.example.pumpking_backend;

import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(@Nonnull CorsRegistry registry) {
                registry.addMapping("/api/games/**")
                        .allowedOrigins("http://pumpking-env.eba-wm32fiaq.eu-north-1.elasticbeanstalk.com")
                        .allowedMethods("GET", "POST", "DELETE");
            }

        };
    }

}
