package me.kire.re.pojogenerator.service;

import me.kire.re.pojogenerator.client.ClientPojoGenerator;
import me.kire.re.pojogenerator.dto.in.DtoInGeneratePojoPost;
import me.kire.re.pojogenerator.factory.FileFactory;
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
