package com.advprog.servletecommerce.application.service;

import com.advprog.servletecommerce.domain.dto.ReviewRequestDto;
import com.advprog.servletecommerce.domain.entities.Review;

import java.util.List;

public interface ReviewService {

    Review createReview(ReviewRequestDto review);

    List<Review> getProductReviews(Long productId);

    Double getProductRating(Long productId);

}