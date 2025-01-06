package me.kire.re.pojogenerator.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "cors.allowed")
public class CorsProperties {
    private String origin;
    private List<String> methods;
    private List<String> headers;
}
