package com.intergalacticmarketjavacourse.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InvalidParam(
        @JsonProperty("name") String name,
        @JsonProperty("reason") String reason
) {}