package com.intergalacticmarketjavacourse.controller;

import com.intergalacticmarketjavacourse.dto.request.ProductCreateRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateCostRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateDescriptionRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateNameRequest;
import com.intergalacticmarketjavacourse.dto.response.PagedProductResponse;
import com.intergalacticmarketjavacourse.dto.response.ProductResponse;
import com.intergalacticmarketjavacourse.service.IProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<PagedProductResponse> getProducts(
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page index must not be less than zero.")
            int page,

            @RequestParam(defaultValue = "20")
            @Min(value = 1, message = "Page size must be at least 1.")
            @Max(value = 100, message = "Page size must not exceed 100.")
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                productService.getProducts(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        ProductResponse response = productService.createProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<ProductResponse> updateProductName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNameRequest request
    ) {
        return ResponseEntity.ok(
                productService.updateProductName(id, request)
        );
    }

    @PatchMapping("/{id}/cost")
    public ResponseEntity<ProductResponse> updateProductCost(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCostRequest request
    ) {
        return ResponseEntity.ok(
                productService.updateProductCost(id, request)
        );
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<ProductResponse> updateProductDescription(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDescriptionRequest request
    ) {
        return ResponseEntity.ok(
                productService.updateProductDescription(id, request)
        );
    }
}

