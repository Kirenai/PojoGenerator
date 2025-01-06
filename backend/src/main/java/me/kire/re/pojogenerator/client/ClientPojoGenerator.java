package me.kire.re.pojogenerator.client;

import me.kire.re.pojogenerator.factory.FileFactory;
import me.kire.re.pojogenerator.model.Pojo;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Path;

public class ClientPojoGenerator {
    private final Mono<Pojo> baseStructure;
    private final Path path;

    public ClientPojoGenerator(FileFactory factory, String text, Path path, Boolean lombok) {
        this.baseStructure = factory.createPojo(text, path, lombok);
        this.path = path;
    }

    public Mono<Path> write() {
        return this.baseStructure.flatMap(Pojo::write)
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(path);
    }
}
