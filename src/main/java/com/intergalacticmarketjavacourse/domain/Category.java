package com.intergalacticmarketjavacourse.domain;

import java.util.Objects;

public class Category {

    private Long id;
    private String name;
    private String description;

    // Конструктор за замовчуванням для JPA
    protected Category() {
    }

    public Category(String name, String description) {
        validateName(name);
        validateDescription(description);

        this.name = name;
        this.description = description;
    }

    public void updateName(String newName) {
        validateName(newName);
        this.name = newName;
    }

    public void updateDescription(String newDescription) {
        validateDescription(newDescription);
        this.description = newDescription;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Category name must not be blank"
            );
        }

        if (name.length() > 100) {
            throw new IllegalArgumentException(
                    "Category name must not exceed 100 characters"
            );
        }
    }

    private void validateDescription(String description) {
        if (description != null && description.length() > 500) {
            throw new IllegalArgumentException(
                    "Category description must not exceed 500 characters"
            );
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;

        return id != null && id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}