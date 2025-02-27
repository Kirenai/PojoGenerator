package me.kire.re.pojogenerator.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import me.kire.re.pojogenerator.domain.port.out.PojoGeneratorPort;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Slf4j
@Getter
@Setter
@Builder
@ToString
public class JsonPojo implements Pojo {

    private Map<Class, List<Attribute>> mapPayload;
    private Boolean lombok;
    private Path outputDirectory;

    private PojoGeneratorPort pojoGeneratorPort;

    @Override
    public Mono<Void> write() {
        if (!mapPayload.isEmpty()) {
            BiConsumer<Class, List<Attribute>> generateJavaFile =
                    (clazz, attributes) -> this.pojoGeneratorPort.generateJavaFile(clazz, attributes, outputDirectory, lombok);
            this.mapPayload.forEach(generateJavaFile);
        } else {
            log.error("The external file is empty");
        }
        return Mono.empty();
    }
}
