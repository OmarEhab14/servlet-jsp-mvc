package com.advprog.servletecommerce.domain.entities;

import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long id;
    private Long userId;
    private Long productId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}