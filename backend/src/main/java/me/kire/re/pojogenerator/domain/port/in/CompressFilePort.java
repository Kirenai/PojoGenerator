package me.kire.re.pojogenerator.domain.port.in;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.io.File;
import java.nio.file.Path;

public interface CompressFilePort {
    Mono<Tuple2<File, Path>> execute(Path path);
}
