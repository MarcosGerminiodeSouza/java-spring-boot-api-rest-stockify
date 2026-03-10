package com.marcos.stockify.domain.product.repository;

import com.marcos.stockify.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByActiveTrue(Pageable pageable);

    Page<Product> findByActiveTrueAndNameContainingIgnoreCase(String name, Pageable pageable);
}
