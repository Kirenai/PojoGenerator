package me.kire.re.pojogenerator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import me.kire.re.pojogenerator.util.PojoUtils;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@Builder
@ToString
public class TextPojo implements Pojo {

    private Map<Class, List<Attribute>> textPayload;
    private Path outputDirectory;

    @Override
    public Mono<Void> write() {
        if (!textPayload.isEmpty()) {
            this.textPayload.forEach((clazz, attributes) -> PojoUtils.generateJavaFile(clazz, attributes, outputDirectory));
        } else {
            log.error("The external file is empty");
        }
        return Mono.empty();
    }
}
