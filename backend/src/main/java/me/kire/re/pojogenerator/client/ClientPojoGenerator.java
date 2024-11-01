package me.kire.re.pojogenerator.client;

import me.kire.re.pojogenerator.factory.FileFactory;
import me.kire.re.pojogenerator.model.Pojo;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ClientPojoGenerator {
    private final Mono<Pojo> baseStructure;

    public ClientPojoGenerator(FileFactory factory, String text) {
        this.baseStructure = factory.createPojo(text);
    }

    public Mono<Void> write() {
        return this.baseStructure.flatMap(Pojo::write)
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
