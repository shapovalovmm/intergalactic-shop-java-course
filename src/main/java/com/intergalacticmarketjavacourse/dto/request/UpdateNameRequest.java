package com.intergalacticmarketjavacourse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateNameRequest(
        @NotBlank(message = "Name must not be blank")
        @Size(min = 1, max = 69, message = "Name must be between 1 and 69 characters")
        String name
) {}