package com.advprog.servletecommerce.application.service.impl;

import com.advprog.servletecommerce.application.mappers.ReviewMapper;
import com.advprog.servletecommerce.application.service.ReviewService;
import com.advprog.servletecommerce.application.validators.ReviewValidator;
import com.advprog.servletecommerce.domain.dao.ReviewDao;
import com.advprog.servletecommerce.domain.dto.CreateReviewRequestDto;
import com.advprog.servletecommerce.domain.entities.Review;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final  ReviewValidator reviewValidator;
    @Override
    public Review createReview(CreateReviewRequestDto review) {

        reviewValidator.validateRating(review);

        Review reviewEntity = ReviewMapper.toEntity(review);
        // 4. Save
        return reviewDao.save(reviewEntity);
    }


    @Override
    public List<Review> getProductReviews(Long productId) {
        return reviewDao.findByProductId(productId);
    }

    @Override
    public Double getProductRating(Long productId) {
        return reviewDao.getAverageRating(productId);
    }
}