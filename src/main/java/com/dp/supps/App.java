package com.dp.supps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // Configure CORS globally versus 
        // controller-by-controller or method-by-method.
        // Can be combined with @CrossOrigin.
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // 1. addMapping("/**") -- opens all URLs.
                //    Was hoping to limit this to /api/**, but that doesn't
                //    include the necessary HTTP Headers for the login endpoints.
                //    The browser blocks requests without them.
                //    Since we're explicitly limiting origins, seems okay to be
                //    less granular.
                // 2. allowedOrigins -- limit to our known and trusted origin.
                //    Trusting a localhost origin is only safe for development.
                // 3. allowCredentials(true) -- turns out this is important.
                //    It tells the client that this server is okay with sharing
                //    cross-origin cookies, an important part of 
                //    Spring Security's form login.
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowCredentials(true);
            }
        };
    }
}
