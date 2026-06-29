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
public class Category {

    private Long id;
    private String categoryCode;
    private String categoryName;
    private boolean active;

    // Constructors
    // Getters
    // Setters
}