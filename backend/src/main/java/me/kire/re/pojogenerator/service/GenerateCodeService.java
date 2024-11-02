package me.kire.re.pojogenerator.service;

import me.kire.re.pojogenerator.client.ClientPojoGenerator;
import me.kire.re.pojogenerator.factory.FileFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@Component
public class GenerateCodeService {
    public Mono<ClientPojoGenerator> execute(FileFactory factory, String text, Path path) {
        ClientPojoGenerator clientPojoGenerator = new ClientPojoGenerator(factory, text, path);
        return Mono.just(clientPojoGenerator);
    }
}
