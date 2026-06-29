package com.ecommerce.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Long id;
	private String productName;
	private Double price;
	private Integer stock;
	private boolean active;

	// Constructors
	// Getters
	// Setters
}
