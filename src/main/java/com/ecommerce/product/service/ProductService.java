
package com.ecommerce.product.service;

import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product.dto.CategoryResponse;
import com.ecommerce.product.dto.CreateCategoryRequest;
import com.ecommerce.product.entity.Category;
import com.ecommerce.product.exception.DuplicateResourceException;
import com.ecommerce.product.exception.BadRequestException;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.status.CategoryStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public CategoryResponse createCategory(CreateCategoryRequest request) {

		log.info("Create Category request received. CategoryCode={}", request.getCategoryCode());

		Optional.ofNullable(request.getCategoryCode()).filter(code -> !code.trim().isEmpty()).orElseThrow(() -> {
			log.error("Category code validation failed.");
			return new BadRequestException("Category Code is required");
		});

		if (productRepository.existsByCategoryCode(request.getCategoryCode())) {

			log.warn("Category already exists. CategoryCode={}", request.getCategoryCode());

			throw new DuplicateResourceException("Category already exists with code : " + request.getCategoryCode());
		}

		Category category = Category.builder().categoryCode(request.getCategoryCode())
				.categoryName(request.getCategoryName()).description(request.getDescription())
				.status(CategoryStatus.ACTIVE).build();

		Category savedCategory = productRepository.save(category);

		return new CategoryResponse().id(savedCategory.getId()).categoryCode(savedCategory.getCategoryCode())
				.categoryName(savedCategory.getCategoryName()).description(savedCategory.getDescription())
				.status(savedCategory.getStatus().name())
				.createdAt(savedCategory.getCreatedAt().atOffset(ZoneOffset.UTC))
				.updatedAt(savedCategory.getUpdatedAt().atOffset(ZoneOffset.UTC));
	}
}
