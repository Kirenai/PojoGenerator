package me.kire.re.pojogenerator.handler;

import lombok.RequiredArgsConstructor;
import me.kire.re.pojogenerator.model.Attribute;
import me.kire.re.pojogenerator.model.Class;
import me.kire.re.pojogenerator.service.PojoGeneratorService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PojoGeneratorHandler {
    private final PojoGeneratorService pojoGeneratorService;

    public Mono<ServerResponse> generate(ServerRequest serverRequest) {
        Mono<Map<Class, List<Attribute>>> response = serverRequest.bodyToMono(String.class)
                .flatMap(this.pojoGeneratorService::generate);
        return ServerResponse.ok().body(response, Map.class);
    }
}
