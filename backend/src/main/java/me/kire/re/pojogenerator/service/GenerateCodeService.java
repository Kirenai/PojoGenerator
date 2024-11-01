package me.kire.re.pojogenerator.service;

import me.kire.re.pojogenerator.client.ClientPojoGenerator;
import me.kire.re.pojogenerator.factory.FileFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GenerateCodeService {
    public Mono<ClientPojoGenerator> execute(FileFactory factory, String text) {
        ClientPojoGenerator clientPojoGenerator = new ClientPojoGenerator(factory, text);
        return Mono.just(clientPojoGenerator);
    }
}
