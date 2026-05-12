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
    private String imageUrl;
    private Integer stockQuantity;
    private String imageUrl;
    private List<Review> reviews;
}
