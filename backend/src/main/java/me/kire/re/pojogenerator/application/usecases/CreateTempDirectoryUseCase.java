package me.kire.re.pojogenerator.application.usecases;

import me.kire.re.pojogenerator.domain.port.in.CreateTempDirectoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class CreateTempDirectoryUseCase implements CreateTempDirectoryPort {
    @Override
    public Mono<Path> execute() {
        return Mono.fromCallable(() -> {
                    String uuid = UUID.randomUUID().toString();
                    Path parentDir = Path.of("temp");

                    if (Files.notExists(parentDir)) {
                        Files.createDirectories(parentDir);
                    }

                    String prefix = "pojo_" + uuid + "_";
                    return Files.createTempDirectory(parentDir, prefix);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorMap(IOException.class, e -> new RuntimeException("Error creating temp directory", e));
    }
}
