package com.intergalacticmarketjavacourse.dto.response;

import java.util.List;

public record PagedProductResponse(
        List content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {}