package me.kire.re.pojogenerator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import me.kire.re.pojogenerator.generator.PojoGenerator;
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

    @Override
    public Mono<Void> write() {
        if (!mapPayload.isEmpty()) {
            PojoGenerator pojoGenerator = new PojoGenerator();
            BiConsumer<Class, List<Attribute>> generateJavaFile =
                    (clazz, attributes) -> pojoGenerator.generateJavaFile(clazz, attributes, outputDirectory, lombok);
            this.mapPayload.forEach(generateJavaFile);
        } else {
            log.error("The external file is empty");
        }
        return Mono.empty();
    }
}
