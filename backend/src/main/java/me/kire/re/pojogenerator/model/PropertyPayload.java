package me.kire.re.pojogenerator.model;

import lombok.Builder;

@Builder
public record PropertyPayload(String leftToken, String rightToken) {
}
