package com.intergalacticmarketjavacourse.dto.request;

import com.intergalacticmarketjavacourse.validation.CosmicWordCheck;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreateRequest(

        @NotBlank(message = "Name must not be blank")
        @Size(
                min = 1,
                max = 69,
                message = "Name must be between 1 and 69 characters"
        )
        @CosmicWordCheck(
                message = "Product name must contain a cosmic term: star, galaxy, comet..."
        )
        String name,

        @NotNull(message = "Cost must not be null")
        @DecimalMin(
                value = "0.0",
                inclusive = true,
                message = "Cost must be greater than or equal to 0.0"
        )
        BigDecimal cost,

        @Size(
                max = 500,
                message = "Description must not exceed 500 characters"
        )
        String description,

        @NotNull(message = "CategoryId must not be null")
        Long categoryId
) {
}