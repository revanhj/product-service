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
public class Order {

	private Long id;
	private Double amount;
	private String status;
	private Customer customer;

	// Constructors
	// Getters
	// Setters
}