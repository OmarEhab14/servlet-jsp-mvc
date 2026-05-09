package com.advprog.servletecommerce.application.validators;

import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.domain.dao.ReviewDao;
import com.advprog.servletecommerce.domain.dto.CreateReviewRequestDto;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ReviewValidator {
    private  final ReviewDao  reviewDao;

    public void validateRating (CreateReviewRequestDto review){
        Map<String, String> errors = new HashMap<>();

        if (review.rating() < 1 || review.rating() > 5) {
            errors.put("rating","Rating must be between 1 and 5");
        }

        if (review.comment() == null || review.comment().isBlank()) {
            errors.put("rating","Comment cannot be empty");

        }

        if (reviewDao.existsByUserAndProduct(review.userId(), review.productId())) {
            errors.put("user","User already reviewed this product");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
