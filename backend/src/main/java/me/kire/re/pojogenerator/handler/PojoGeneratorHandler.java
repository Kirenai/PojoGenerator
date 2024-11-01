package me.kire.re.pojogenerator.handler;

import lombok.RequiredArgsConstructor;
import me.kire.re.pojogenerator.service.PojoGeneratorService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PojoGeneratorHandler {
    private final PojoGeneratorService pojoGeneratorService;

    public Mono<ServerResponse> generate(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(String.class)
                .flatMap(this.pojoGeneratorService::generate)
                .then(ServerResponse.ok().build());
    }
}
