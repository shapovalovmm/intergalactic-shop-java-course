package com.intergalacticmarketjavacourse.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cart {

    private Long id;
    private List<Product> products;
    private BigDecimal totalCost;

    // Конструктор за замовчуванням для JPA
    protected Cart() {
    }

    public Cart(List<Product> products) {
        Objects.requireNonNull(products, "Products must not be null");

        this.products = new ArrayList<>(products);
        recalculateTotalCost();
    }

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Product must not be null");

        products.add(product);
        recalculateTotalCost();
    }

    public void removeProduct(Product product) {
        if (product == null) {
            return;
        }

        products.remove(product);
        recalculateTotalCost();
    }

    public void clear() {
        products.clear();
        totalCost = BigDecimal.ZERO;
    }

    public Order checkout() {
        if (products.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot create an order from an empty cart"
            );
        }

        return new Order(products);
    }

    private void recalculateTotalCost() {
        totalCost = products.stream()
                .map(Product::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;

        return id != null && id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}