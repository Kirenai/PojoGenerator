package me.kire.re.pojogenerator.domain.port.in;

import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Path;

public interface DeleteDirectoryPort {
    Mono<File> execute(Path directory, File file);
}
