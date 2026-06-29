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
public class Address {

    private String city;
    private String state;
    private String country;
    private String zipCode;

    // Constructors
    // Getters
    // Setters
}