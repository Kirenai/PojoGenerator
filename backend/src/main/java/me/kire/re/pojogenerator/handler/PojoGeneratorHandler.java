package me.kire.re.pojogenerator.handler;

import lombok.RequiredArgsConstructor;
import me.kire.re.pojogenerator.dto.in.DtoInGeneratePojoPost;
import me.kire.re.pojogenerator.service.PojoGeneratorService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PojoGeneratorHandler {
    private final PojoGeneratorService pojoGeneratorService;

    public Mono<ServerResponse> generate(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(DtoInGeneratePojoPost.class)
                .flatMap(this.pojoGeneratorService::generate)
                .flatMap(file -> {
                    FileSystemResource resource = new FileSystemResource(file);
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_OCTET_STREAM)
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                            .bodyValue(resource);
                });
    }
}
