package com.intergalacticmarketjavacourse.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record UpdateCostRequest(
        @NotNull(message = "Cost must not be null")
        @DecimalMin(value = "0.0", inclusive = true, message = "Cost must be greater than or equal to 0.0")
        BigDecimal cost
) {}