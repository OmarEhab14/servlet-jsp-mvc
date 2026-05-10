package com.advprog.servletecommerce.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetails {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private List<Review> reviews;
}
