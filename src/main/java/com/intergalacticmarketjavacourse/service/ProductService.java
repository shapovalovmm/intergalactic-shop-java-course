package com.intergalacticmarketjavacourse.service;

import com.intergalacticmarketjavacourse.domain.Product;
import com.intergalacticmarketjavacourse.dto.request.ProductCreateRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateCostRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateDescriptionRequest;
import com.intergalacticmarketjavacourse.dto.request.UpdateNameRequest;
import com.intergalacticmarketjavacourse.dto.response.PagedProductResponse;
import com.intergalacticmarketjavacourse.dto.response.ProductResponse;
import com.intergalacticmarketjavacourse.exception.ProductNotFoundException;
import com.intergalacticmarketjavacourse.mapper.ProductMapper;
import com.intergalacticmarketjavacourse.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public PagedProductResponse getProducts(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        return productMapper.toPagedDto(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        Product product = productMapper.toDomain(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductResponse updateProductName(Long id, UpdateNameRequest request) {
        Product product = findProductOrThrow(id);
        product.updateName(request.name());
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProductCost(Long id, UpdateCostRequest request) {
        Product product = findProductOrThrow(id);
        product.updateCost(request.cost());
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProductDescription(Long id, UpdateDescriptionRequest request) {
        Product product = findProductOrThrow(id);
        product.updateDescription(request.description());
        return productMapper.toDto(product);
    }

    private Product findProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}