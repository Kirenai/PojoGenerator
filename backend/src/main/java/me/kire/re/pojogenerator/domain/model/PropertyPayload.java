package me.kire.re.pojogenerator.domain.model;

import lombok.Builder;

@Builder
public record PropertyPayload(String leftToken, String rightToken) {
}
