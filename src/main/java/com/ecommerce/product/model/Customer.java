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
public class Customer {

    private Long id;
    private String name;
    private String email;
    private String mobile;
    private Double creditLimit;

    // Constructors
    // Getters
    // Setters
}