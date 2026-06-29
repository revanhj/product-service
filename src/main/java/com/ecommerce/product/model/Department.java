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
public class Department {

    private Long id;
    private String departmentName;
    private String location;

    // Constructors
    // Getters
    // Setters
}