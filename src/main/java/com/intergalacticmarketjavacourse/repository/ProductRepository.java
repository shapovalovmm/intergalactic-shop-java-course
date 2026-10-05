package com.intergalacticmarketjavacourse.repository;

import com.intergalacticmarketjavacourse.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}