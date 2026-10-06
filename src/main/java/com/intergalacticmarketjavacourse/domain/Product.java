package com.intergalacticmarketjavacourse.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long id;
    private ProductName name;
    private BigDecimal cost;
    private String description;
    private Long categoryId;

    // Конструктор за замовчуванням для JPA
    protected Product() {
    }

    public Product(String name, BigDecimal cost, String description, Long categoryId) {
        validateCost(cost);
        validateDescription(description);
        this.categoryId = Objects.requireNonNull(categoryId, "Category ID must not be null");

        this.name = new ProductName(name);
        this.cost = cost;
        this.description = description;
    }

    // Бізнес-методи для мутації стану сутності (відповідають PATCH операціям у контракті)

    public void updateName(String newName) {
        this.name = new ProductName(newName);
    }

    public void updateCost(BigDecimal newCost) {
        validateCost(newCost);
        this.cost = newCost;
    }

    public void updateDescription(String newDescription) {
        validateDescription(newDescription);
        this.description = newDescription;
    }

    // Доменна валідація інваріантів

    private void validateCost(BigDecimal cost) {
        if (cost == null) {
            throw new IllegalArgumentException("Product cost must not be null");
        }
        if (cost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product cost must be greater than or equal to 0");
        }
    }

    private void validateDescription(String description) {
        if (description != null && description.length() > 500) {
            throw new IllegalArgumentException("Product description must not exceed 500 characters");
        }
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.value();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id != null && id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}