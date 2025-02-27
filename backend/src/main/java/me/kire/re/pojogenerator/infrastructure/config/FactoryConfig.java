package me.kire.re.pojogenerator.infrastructure.config;

import me.kire.re.pojogenerator.domain.factory.FileFactory;
import me.kire.re.pojogenerator.domain.port.out.PojoGeneratorPort;
import me.kire.re.pojogenerator.domain.port.out.TextProcessorPort;
import me.kire.re.pojogenerator.infrastructure.adapter.factory.JsonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {
    @Bean
    public FileFactory jsonFactory(TextProcessorPort textProcessorPort, PojoGeneratorPort pojoGeneratorPort) {
        return new JsonFactory(textProcessorPort, pojoGeneratorPort);
    }
}
