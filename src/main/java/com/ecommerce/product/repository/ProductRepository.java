package com.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.product.entity.Category;

import jakarta.validation.constraints.NotNull;

public interface ProductRepository extends JpaRepository<Category, Long> {

	boolean existsByCategoryCode(@NotNull String categoryCode);

}
