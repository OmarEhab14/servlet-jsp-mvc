package com.advprog.servletecommerce.domain.dao;

import com.advprog.servletecommerce.domain.entities.Review;

import java.util.List;

public interface ReviewDao {
    Review save(Review review);

    List<Review> findByProductId(Long productId);

    boolean existsByUserAndProduct(Long userId, Long productId);

    double getAverageRating(Long productId);

}
