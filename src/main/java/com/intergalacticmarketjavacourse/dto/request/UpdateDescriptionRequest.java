package com.intergalacticmarketjavacourse.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateDescriptionRequest(
        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description
) {}