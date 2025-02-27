package me.kire.re.pojogenerator.domain.model;

import reactor.core.publisher.Mono;

public interface Pojo {

    Mono<Void> write();

}
