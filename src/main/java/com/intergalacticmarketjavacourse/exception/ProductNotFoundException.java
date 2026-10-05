package com.intergalacticmarketjavacourse.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Product with ID " + id + " does not exist.");
    }
}