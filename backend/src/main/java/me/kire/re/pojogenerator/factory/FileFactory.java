package me.kire.re.pojogenerator.factory;

import me.kire.re.pojogenerator.model.Pojo;
import reactor.core.publisher.Mono;

public interface FileFactory {

    Mono<Pojo> createPojo(String text);

}
