package me.kire.re.pojogenerator.domain.port.out;

import me.kire.re.pojogenerator.domain.model.PropertyPayload;
import reactor.core.publisher.Flux;

public interface TextProcessorPort {
    Flux<PropertyPayload> process(String text);
}
