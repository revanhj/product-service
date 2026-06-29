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
public class Employee {

    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private String email;
    private String mobile;
    private boolean active;
    private Department department;
    private Address address;

    // Constructors
    // Getters
    // Setters
}