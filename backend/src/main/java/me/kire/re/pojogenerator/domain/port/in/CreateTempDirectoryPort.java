package me.kire.re.pojogenerator.domain.port.in;

import reactor.core.publisher.Mono;

import java.nio.file.Path;

public interface CreateTempDirectoryPort {
    Mono<Path> execute();
}
