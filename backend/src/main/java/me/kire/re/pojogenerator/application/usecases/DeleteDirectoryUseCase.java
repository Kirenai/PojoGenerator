package me.kire.re.pojogenerator.application.usecases;

import me.kire.re.pojogenerator.domain.port.in.DeleteDirectoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class DeleteDirectoryUseCase implements DeleteDirectoryPort {
    @Override
    public Mono<File> execute(Path directory, File file) {
        return Mono.fromCallable(() -> {
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
            return file;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
