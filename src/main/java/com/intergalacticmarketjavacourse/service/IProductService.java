package com.intergalacticmarketjavacourse.service;

import com.intergalacticmarketjavacourse.dto.request.ProductCreateRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateCostRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateDescriptionRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateNameRequest;
import com.intergalacticmarketjavacourse.dto.response.PagedProductResponse;
import com.intergalacticmarketjavacourse.dto.response.ProductResponse;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    PagedProductResponse getProducts(Pageable pageable);

    ProductResponse getProductById(Long id);

    ProductResponse createProduct(ProductCreateRequest request);

    void deleteProduct(Long id);

    ProductResponse updateProductName(Long id, UpdateNameRequest request);

    ProductResponse updateProductCost(Long id, UpdateCostRequest request);

    ProductResponse updateProductDescription(Long id, UpdateDescriptionRequest request);
}