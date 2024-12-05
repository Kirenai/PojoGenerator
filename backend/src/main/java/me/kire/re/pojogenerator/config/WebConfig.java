package me.kire.re.pojogenerator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig {
    @Value("${allowed.origin}")
    private String allowedOrigin;
    @Value("${allowed.methods}")
    private String allowedMethods;
    @Value("${allowed.headers}")
    private String allowedHeaders;

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin(this.allowedOrigin);
        config.addAllowedHeader(this.allowedHeaders);
        config.addExposedHeader(HttpHeaders.CONTENT_DISPOSITION);
        config.addAllowedMethod(this.allowedMethods);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
