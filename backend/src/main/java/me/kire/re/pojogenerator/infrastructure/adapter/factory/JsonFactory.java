package me.kire.re.pojogenerator.infrastructure.adapter.factory;

import lombok.RequiredArgsConstructor;
import me.kire.re.pojogenerator.domain.factory.FileFactory;
import me.kire.re.pojogenerator.domain.model.Attribute;
import me.kire.re.pojogenerator.domain.model.Class;
import me.kire.re.pojogenerator.domain.model.JsonPojo;
import me.kire.re.pojogenerator.domain.model.Pojo;
import me.kire.re.pojogenerator.domain.model.PropertyPayload;
import me.kire.re.pojogenerator.domain.port.out.PojoGeneratorPort;
import me.kire.re.pojogenerator.domain.port.out.TextProcessorPort;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static me.kire.re.pojogenerator.util.Constants.ARRAY_BODY;
import static me.kire.re.pojogenerator.util.Constants.ARRAY_POSTFIX;
import static me.kire.re.pojogenerator.util.Constants.ARRAY_PREFIX;
import static me.kire.re.pojogenerator.util.StringUtils.removeArrayFormat;

@RequiredArgsConstructor
public class JsonFactory implements FileFactory {
    private final TextProcessorPort textProcessorPort;
    private final PojoGeneratorPort pojoGeneratorPort;

    @Override
    public Mono<Pojo> createPojo(String text, Path path, Boolean lombok) {
        return Mono.defer(() -> {
            JsonPojo.JsonPojoBuilder builder = JsonPojo.builder();
            Flux<PropertyPayload> read = this.textProcessorPort.process(text);

            return this.cache(read)
                    .doOnNext(map -> {
                        builder.mapPayload(map);
                        builder.outputDirectory(path);
                        builder.lombok(lombok);
                        builder.pojoGeneratorPort(this.pojoGeneratorPort);
                    })
                    .then(Mono.fromCallable(builder::build));
        });
    }

    private Mono<Map<Class, List<Attribute>>> cache(Flux<PropertyPayload> rows) {
        return rows.collectList()
                .map(list -> {
                    Map<Class, List<Attribute>> map = new LinkedHashMap<>();
                    list.forEach(payload -> {
                        String leftToken = payload.leftToken();
                        String rightToken = payload.rightToken();

                        String[] tokens = leftToken.split("\\.");

                        int firstIndex = 0;
                        String className = tokens[firstIndex];

                        Class classKey = this.buildParentClass(className, rightToken);

                        for (int attributeIndex = 0; attributeIndex < tokens.length; attributeIndex++) {
                            classKey = this.defineDefaultNameOrGivenName(tokens, classKey, attributeIndex, rightToken);

                            if (this.isLastIndex(attributeIndex, tokens)) {
                                break;
                            }

                            int nextAttributeIndex = attributeIndex + 1;
                            String name = tokens[nextAttributeIndex];
                            Attribute attributeValue = this.buildAttribute(name, rightToken, this.isLastIndex(nextAttributeIndex, tokens));

                            if (map.containsKey(classKey)) {
                                List<Attribute> attributes = map.get(classKey);
                                if (!attributes.contains(attributeValue)) {
                                    attributes.add(attributeValue);
                                }
                                continue;
                            }

                            List<Attribute> attributes = new ArrayList<>();
                            attributes.add(attributeValue);
                            map.put(classKey, attributes);
                        }
                    });
                    return map;
                });
    }

    private boolean isLastIndex(int attributeIndex, String[] splitAttribute) {
        return attributeIndex == splitAttribute.length - 1;
    }

    private Class defineDefaultNameOrGivenName(String[] tokens, Class classKey, int attributeIndex, String io) {
        if (attributeIndex == 0) {
            return classKey;
        }
        String attributeName = tokens[attributeIndex];
        return this.buildParentClass(attributeName, io);
    }

    private Class buildParentClass(String name, String io) {
        name = StringUtils.capitalize(name);
        return Class.builder()
                .name(removeArrayFormat(name))
                .ioType(io)
                .isObject(isObject(name))
                .isArray(isArray(name))
                .build();
    }


    private Attribute buildAttribute(String name, String io, boolean isLastIndex) {
        boolean isObject = !isLastIndex;

        String type = "String";
        if (isObject) {
            type = StringUtils.capitalize(name);
        }

        return Attribute.builder()
                .type(removeArrayFormat(type))
                .name(removeArrayFormat(name))
                .ioType(io)
                .isObject(isObject)
                .isArray(isArray(name))
                .build();
    }

    private boolean isObject(String name) {
        return name.startsWith(name.substring(0, 1).toUpperCase());
    }

    private boolean isArray(String name) {
        return name.contains(ARRAY_BODY) || name.contains(ARRAY_PREFIX) || name.contains(ARRAY_POSTFIX);
    }

}
