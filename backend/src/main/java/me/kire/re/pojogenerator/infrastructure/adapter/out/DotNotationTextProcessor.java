package me.kire.re.pojogenerator.infrastructure.adapter.out;

import me.kire.re.pojogenerator.domain.model.PropertyPayload;
import me.kire.re.pojogenerator.domain.port.out.TextProcessorPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Component
public class DotNotationTextProcessor implements TextProcessorPort {
    @Override
    public Flux<PropertyPayload> process(String text) {
        String[] row = text.split("\n");
        return Flux.fromIterable(Arrays.asList(row))
                .map(line -> line.split(" - "))
                .filter(parts -> parts.length == 2)
                .map(token -> PropertyPayload.builder().leftToken(token[0].trim()).rightToken(token[1].trim()).build());
    }
}
