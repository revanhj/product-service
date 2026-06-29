package com.ecommerce.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.product.dto.CategoryResponse;
import com.ecommerce.product.dto.CreateCategoryRequest;
import com.ecommerce.product.entity.Category;
import com.ecommerce.product.exception.BadRequestException;
import com.ecommerce.product.exception.DuplicateResourceException;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.service.ProductService;
import com.ecommerce.product.status.CategoryStatus;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void shouldCreateCategorySuccessfully() {

		CreateCategoryRequest request = getRequest();

		Category category = getCategory();
		when(productRepository.existsByCategoryCode("MOB")).thenReturn(false);
		when(productRepository.save(any(Category.class))).thenReturn(category);
		CategoryResponse response = productService.createCategory(request);

		assertNotNull(response);
		assertEquals(1L, response.getId());
		assertEquals("MOB", response.getCategoryCode());
		assertEquals("Mobiles", response.getCategoryName());
		assertEquals("Mobile Category", response.getDescription());
		assertEquals("ACTIVE", response.getStatus());
		assertNotNull(response.getCreatedAt());
		assertNotNull(response.getUpdatedAt());

		verify(productRepository, times(1)).existsByCategoryCode("MOB");
		verify(productRepository, times(1)).save(any(Category.class));
	}

	@Test
	void shouldThrowBadRequestExceptionWhenCategoryCodeIsNull() {

		CreateCategoryRequest request = getRequest();
		request.setCategoryCode(null);

		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> productService.createCategory(request));

		assertEquals("Category Code is required", exception.getMessage());

		verify(productRepository, never()).existsByCategoryCode(anyString());

		verify(productRepository, never()).save(any());
	}

	@Test
	void shouldThrowBadRequestExceptionWhenCategoryCodeIsBlank() {

		CreateCategoryRequest request = getRequest();
		request.setCategoryCode(" ");

		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> productService.createCategory(request));

		assertEquals("Category Code is required", exception.getMessage());

		verify(productRepository, never()).existsByCategoryCode(anyString());

		verify(productRepository, never()).save(any());
	}

	@Test
	void shouldThrowDuplicateResourceException() {

		CreateCategoryRequest request = getRequest();

		when(productRepository.existsByCategoryCode("MOB")).thenReturn(true);
		DuplicateResourceException exception = assertThrows(DuplicateResourceException.class,
				() -> productService.createCategory(request));
		assertEquals("Category already exists with code : MOB", exception.getMessage());
		verify(productRepository, times(1)).existsByCategoryCode("MOB");
		verify(productRepository, never()).save(any());
	}

	@Test
	void shouldCallRepositorySaveOnlyOnce() {

		when(productRepository.existsByCategoryCode(anyString())).thenReturn(false);
		when(productRepository.save(any())).thenReturn(getCategory());
		productService.createCategory(getRequest());
		verify(productRepository, times(1)).save(any(Category.class));
	}

	@Test
	void shouldMapCategoryResponseCorrectly() {

		when(productRepository.existsByCategoryCode(anyString())).thenReturn(false);

		when(productRepository.save(any())).thenReturn(getCategory());

		CategoryResponse response = productService.createCategory(getRequest());

		assertAll(() -> assertEquals(1L, response.getId()), () -> assertEquals("MOB", response.getCategoryCode()),
				() -> assertEquals("Mobiles", response.getCategoryName()),
				() -> assertEquals("Mobile Category", response.getDescription()),
				() -> assertEquals("ACTIVE", response.getStatus()), () -> assertNotNull(response.getCreatedAt()),
				() -> assertNotNull(response.getUpdatedAt())

		);
	}

	@Test
	void shouldNotCallRepositoryWhenValidationFails() {

		CreateCategoryRequest request = getRequest();
		request.setCategoryCode(null);

		assertThrows(BadRequestException.class, () -> productService.createCategory(request));
		verify(productRepository, never()).existsByCategoryCode(anyString());
		verify(productRepository, never()).save(any());
	}

	private CreateCategoryRequest getRequest() {

		CreateCategoryRequest request = new CreateCategoryRequest();

		request.setCategoryCode("MOB");
		request.setCategoryName("Mobiles");
		request.setDescription("Mobile Category");
		return request;
	}

	private Category getCategory() {
		return Category.builder().id(1L).categoryCode("MOB").categoryName("Mobiles").description("Mobile Category")
				.status(CategoryStatus.ACTIVE).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
	}
}