package me.kire.re.pojogenerator.router;

import me.kire.re.pojogenerator.handler.PojoGeneratorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PojoGeneratorRouter {
    @Bean
    public RouterFunction<ServerResponse> router(PojoGeneratorHandler handler) {
        return RouterFunctions.route()
                .POST("/generate", RequestPredicates.accept(MediaType.APPLICATION_OCTET_STREAM)
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::generate)
                .build();
    }
}
