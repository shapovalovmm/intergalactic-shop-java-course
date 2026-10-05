package com.intergalacticmarketjavacourse.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ValidationProblemDetails(
        URI type,
        String title,
        int status,
        String detail,
        URI instance,
        @JsonProperty("invalid-params") List invalidParams
) {}