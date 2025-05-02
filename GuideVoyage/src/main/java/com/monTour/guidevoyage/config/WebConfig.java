package com.monTour.guidevoyage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configuration pour servir les fichiers statiques depuis le dossier resources/static
        registry.addResourceHandler("/static/**", "/images/**", "/uploads/**")
                .addResourceLocations(
                    "classpath:/static/",
                    "classpath:/static/images/",
                    "file:uploads/"
                );
    }
} 