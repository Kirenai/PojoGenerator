package me.kire.re.pojogenerator.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class TemporaryFileCleanupService {

    private static final Path TEMP_DIR = Path.of("temp");

    @Scheduled(cron = "0 */15 * * * *")
    public void cleanup() {
        try (Stream<Path> walk = Files.walk(TEMP_DIR)) {
            walk.filter(path -> path.toString().endsWith(".zip"))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
