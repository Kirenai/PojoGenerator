package me.kire.re.pojogenerator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@Builder
@ToString
public class TextPojo implements Pojo {

    private Map<Class, List<Attribute>> textPayload;

    @Override
    public Mono<Void> write() {
        if (!textPayload.isEmpty()) {
            this.textPayload.forEach((clazz, attributes) -> System.out.println(clazz + " " + attributes));
        } else {
            log.error("The external file is empty");
        }
        return Mono.empty();
    }
}
