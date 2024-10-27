package me.kire.re.pojogenerator.model;

import lombok.Builder;

@Builder
public record Class(
        String name,
        String ioType,
        Boolean isObject,
        Boolean isArray
) {
}
