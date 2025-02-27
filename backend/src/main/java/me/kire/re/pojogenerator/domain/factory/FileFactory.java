package me.kire.re.pojogenerator.domain.factory;

import me.kire.re.pojogenerator.domain.model.Pojo;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

public interface FileFactory {

    Mono<Pojo> createPojo(String text, Path path, Boolean lombok);

}
