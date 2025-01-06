package me.kire.re.pojogenerator.factory;

import me.kire.re.pojogenerator.model.Pojo;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

public interface FileFactory {

    Mono<Pojo> createPojo(String text, Path path, Boolean lombok);

}
