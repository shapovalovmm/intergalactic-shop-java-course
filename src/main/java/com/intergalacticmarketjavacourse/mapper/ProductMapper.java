package com.intergalacticmarketjavacourse.mapper;

import com.intergalacticmarketjavacourse.domain.Product;
import com.intergalacticmarketjavacourse.dto.request.ProductCreateRequest;
import com.intergalacticmarketjavacourse.dto.response.PagedProductResponse;
import com.intergalacticmarketjavacourse.dto.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductResponse toDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCost(),
                product.getDescription(),
                product.getCategoryId()
        );
    }

    public PagedProductResponse toPagedDto(
            List<Product> products,
            int page,
            int size,
            long totalElements,
            int totalPages
    ) {
        List<ProductResponse> content = products.stream()
                .map(this::toDto)
                .toList();

        return new PagedProductResponse(
                content,
                page,
                size,
                totalElements,
                totalPages
        );
    }

    public Product toDomain(ProductCreateRequest request) {
        if (request == null) {
            return null;
        }

        return new Product(
                request.name(),
                request.cost(),
                request.description(),
                request.categoryId()
        );
    }

}