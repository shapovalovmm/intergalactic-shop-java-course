package com.intergalacticmarketjavacourse.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order {

    private Long id;
    private List<Product> products;
    private BigDecimal totalCost;
    private OrderStatus status;

    protected Order() {
    }

    public Order(List<Product> products) {
        Objects.requireNonNull(products, "Products must not be null");

        this.products = new ArrayList<>(products);
        this.status = OrderStatus.CREATED;
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

    public void confirm() {
        if (products.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot confirm an empty order"
            );
        }

        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException(
                    "Only created orders can be confirmed"
            );
        }

        status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        if (status == OrderStatus.DELIVERED) {
            throw new IllegalStateException(
                    "Delivered order cannot be cancelled"
            );
        }

        status = OrderStatus.CANCELLED;
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

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        return id != null && id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}