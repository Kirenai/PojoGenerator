package me.kire.re.pojogenerator.model;

import reactor.core.publisher.Mono;

public interface Pojo {

    Mono<Void> write();

}
