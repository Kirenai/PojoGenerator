package me.kire.re.pojogenerator.service;

import lombok.RequiredArgsConstructor;
import me.kire.re.pojogenerator.client.ClientPojoGenerator;
import me.kire.re.pojogenerator.factory.TextFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PojoGeneratorService {
    private final GenerateCodeService generateCodeService;

    public Mono<Void> generate(String text) {
        return this.createTempDirectory()
                .flatMap(path -> this.generateCodeService.execute(new TextFactory(), text)
                        .flatMap(ClientPojoGenerator::write)
                        .then(this.deleteDirectory(path)))
                .then();
    }

    private Mono<Path> createTempDirectory() {
        return Mono.fromCallable(() -> {
                    String uuid = UUID.randomUUID().toString();
                    Path parentDir = Path.of("temp");

                    if (Files.notExists(parentDir)) {
                        Files.createDirectory(parentDir);
                    }

                    String prefix = "pojo_" + uuid + "_";
                    return Files.createTempDirectory(parentDir, prefix);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorMap(IOException.class, e -> new RuntimeException("Error creating temp directory", e));
    }

    private Mono<Void> deleteDirectory(Path directory) {
        return Mono.fromRunnable(() -> {
                    try (Stream<Path> walk = Files.walk(directory)) {
                        walk.sorted(Comparator.reverseOrder())
                                .forEach(path -> {
                                    try {
                                        Files.delete(path);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
