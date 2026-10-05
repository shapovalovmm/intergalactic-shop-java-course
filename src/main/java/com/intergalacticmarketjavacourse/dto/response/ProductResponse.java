package com.intergalacticmarketjavacourse.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal cost,
        String description,
        Long categoryId
) {}