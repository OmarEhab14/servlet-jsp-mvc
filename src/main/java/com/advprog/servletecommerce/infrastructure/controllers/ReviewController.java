package com.advprog.servletecommerce.infrastructure.controllers;
import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.application.service.ReviewService;
import com.advprog.servletecommerce.configs.AppConfig;
import com.advprog.servletecommerce.domain.dto.CreateReviewRequestDto;
import com.advprog.servletecommerce.domain.entities.Review;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;

import java.io.IOException;
import java.util.List;

@Builder
@WebServlet("/reviews/*")
public class ReviewController extends HttpServlet {
    private ReviewService reviewService;
    @Override
    public void init() {
        reviewService = AppConfig.getReviewService();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long userId = (Long) request.getAttribute("userId");
        Long productId = Long.parseLong(request.getParameter("productId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        CreateReviewRequestDto reviewDto = new CreateReviewRequestDto(userId, productId, rating, comment
        );

        try {



            Review saved = reviewService.createReview(reviewDto);

//            writeJson(response, saved, 201);

        } catch (ValidationException ex) {
            request.setAttribute("error", ex);
            request.setAttribute("oldDto", reviewDto);

//            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
//                    .forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String path = request.getPathInfo();

            // /reviews/product/{id}
            if (path != null && path.startsWith("/product/")) {

                Long productId = Long.parseLong(path.split("/")[2]);

                List<Review> reviews = reviewService.getProductReviews(productId);

            }

        } catch (Exception e) {
        }
    }
}
