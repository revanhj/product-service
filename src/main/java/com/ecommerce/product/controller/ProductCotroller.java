package com.ecommerce.product.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.ecommerce.product.api.CategoryApi;
import com.ecommerce.product.dto.CategoryResponse;
import com.ecommerce.product.dto.CreateCategoryRequest;
import com.ecommerce.product.dto.UpdateCategoryRequest;
import com.ecommerce.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductCotroller implements CategoryApi {

	private final ProductService productService;

	@Override
	public ResponseEntity<CategoryResponse> createCategory(CreateCategoryRequest request) {

		log.info("Create category request received");

		CategoryResponse response = productService.createCategory(request);
		URI location = URI.create("/api/v1/categories/" + response.getId());

		log.info("Category created successfully. Id={}", response.getId());

		return ResponseEntity.created(location).body(response);
	}

	@Override
	public ResponseEntity<Void> _deleteCategory(Long id) {
		// TODO Auto-generated method stub
		return CategoryApi.super._deleteCategory(id);
	}

	@Override
	public ResponseEntity<Void> deleteCategory(Long id) {
		// TODO Auto-generated method stub
		return CategoryApi.super.deleteCategory(id);
	}

	@Override
	public ResponseEntity<List<CategoryResponse>> _getAllCategories() {
		// TODO Auto-generated method stub
		return CategoryApi.super._getAllCategories();
	}

	@Override
	public ResponseEntity<List<CategoryResponse>> getAllCategories() {
		// TODO Auto-generated method stub
		return CategoryApi.super.getAllCategories();
	}

	@Override
	public ResponseEntity<CategoryResponse> _getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return CategoryApi.super._getCategoryById(id);
	}

	@Override
	public ResponseEntity<CategoryResponse> getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return CategoryApi.super.getCategoryById(id);
	}

	@Override
	public ResponseEntity<Void> _updateCategory(Long id, @Valid UpdateCategoryRequest updateCategoryRequest) {
		// TODO Auto-generated method stub
		return CategoryApi.super._updateCategory(id, updateCategoryRequest);
	}

	@Override
	public ResponseEntity<Void> updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) {
		// TODO Auto-generated method stub
		return CategoryApi.super.updateCategory(id, updateCategoryRequest);
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return CategoryApi.super.getRequest();
	}
}