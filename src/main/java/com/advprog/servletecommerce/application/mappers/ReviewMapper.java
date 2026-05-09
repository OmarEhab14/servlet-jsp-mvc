package com.advprog.servletecommerce.application.mappers;

import com.advprog.servletecommerce.domain.dto.CreateReviewRequestDto;
import com.advprog.servletecommerce.domain.entities.Review;

public class ReviewMapper {
    public static Review toEntity(CreateReviewRequestDto reviewRequest) {
        return Review.builder()
                .productId(reviewRequest.productId())
                .rating(reviewRequest.rating())
                .comment(reviewRequest.comment())
                .userId(reviewRequest.userId())
                .build();
    }
}
