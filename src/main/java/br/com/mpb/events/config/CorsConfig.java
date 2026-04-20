package br.com.mpb.events.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://127.0.0.1:5500",   // Live Server do VSCode
                        "http://localhost:5500",   // alternativa
                        "http://localhost:3000"    // caso use React ou outro frontend
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);

        // Em produção, você pode trocar para:
        // registry.addMapping("/**")
        //         .allowedOrigins("https://meusistema.com")
        //         .allowedMethods("GET", "POST", "PUT", "DELETE")
        //         .allowedHeaders("*");
    }
}

