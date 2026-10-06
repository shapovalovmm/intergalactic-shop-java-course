package com.intergalacticmarketjavacourse.domain;

import java.util.Set;

public record ProductName(String value) {

    public static final int MAX_LENGTH = 69;
    public static final Set<String> COSMIC_TERMS =
            Set.of("star", "galaxy", "comet");

    public ProductName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Product name must not be blank");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Product name must not exceed " + MAX_LENGTH + " characters"
            );
        }

        String normalized = value.toLowerCase();

        boolean hasCosmicTerm =
                COSMIC_TERMS.stream().anyMatch(normalized::contains);

        if (!hasCosmicTerm) {
            throw new IllegalArgumentException(
                    "Product name must contain a cosmic term: " + COSMIC_TERMS
            );
        }
    }

    public static boolean containsCosmicTerm(String val) {
        if (val == null) {
            return false;
        }

        String normalized = val.toLowerCase();

        return COSMIC_TERMS.stream().anyMatch(normalized::contains);
    }
}