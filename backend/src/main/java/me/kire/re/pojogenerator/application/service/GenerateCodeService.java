package me.kire.re.pojogenerator.application.service;

import me.kire.re.pojogenerator.application.client.ClientPojoGenerator;
import me.kire.re.pojogenerator.application.dto.in.DtoInGeneratePojoPost;
import me.kire.re.pojogenerator.domain.factory.FileFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@Service
public class GenerateCodeService {
    public Mono<ClientPojoGenerator> execute(FileFactory factory, DtoInGeneratePojoPost request, Path path) {
        ClientPojoGenerator clientPojoGenerator = new ClientPojoGenerator(factory, request.getText(), path, request.getLombok());
        return Mono.just(clientPojoGenerator);
    }
}
