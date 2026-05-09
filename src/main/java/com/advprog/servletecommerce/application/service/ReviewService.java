package com.advprog.servletecommerce.application.service;

import com.advprog.servletecommerce.domain.dto.CreateReviewRequestDto;
import com.advprog.servletecommerce.domain.entities.Review;

import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequestDto review);

    List<Review> getProductReviews(Long productId);

    Double getProductRating(Long productId);

}