package com.advprog.servletecommerce.infrastructure.controllers;
import com.advprog.servletecommerce.application.exceptions.AppException;
import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.application.service.ReviewService;
import com.advprog.servletecommerce.configs.AppConfig;
import com.advprog.servletecommerce.domain.dto.ReviewRequestDto;
import com.advprog.servletecommerce.domain.entities.Review;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;

import java.io.IOException;
import java.util.List;

@WebServlet("/reviews/*")
public class ReviewController extends HttpServlet {
    private ReviewService reviewService;
    @Override
    public void init() {
        reviewService = AppConfig.getReviewService();
    }

    ///  /reviews/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ///  userId injected in AuthFilter
        try {
            Long userId = (Long) request.getAttribute("userId");

            Long productId = extractProductId(request);
                ///  from request body in GUI
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("comment");
            ReviewRequestDto reviewDto = new ReviewRequestDto(userId, productId, rating, comment
            );
            reviewService.createReview(reviewDto);

            response.sendRedirect(request.getContextPath() + "/reviews/product/" + productId);
        }
        catch (ValidationException e) {
            request.setAttribute(
                    "error",
                    e.getMessage());

            request.getRequestDispatcher(
                            "/WEB-INF/views/reviews/review.jsp")
                    .forward(request, response);
        }

        catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            response.sendError(500);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

                // /reviews/product/{id}
                Long productId = extractProductId(request);
                List<Review> reviews = reviewService.getProductReviews(productId);

                request.setAttribute("reviews", reviews);
                /// GUI of review
                request.getRequestDispatcher("/WEB-INF/views/reviews/review.jsp").forward(request, response);

        } catch (AppException e) {
            request.setAttribute("error", e.getMessage());
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private Long extractProductId(HttpServletRequest request) {

        String path = request.getPathInfo();

        if (path == null) {
            throw new IllegalArgumentException("Missing path");
        }

        String[] parts = path.split("/");

        //  /product/{id}
        if (parts.length < 3 || !"product".equals(parts[1])) {
            throw new IllegalArgumentException("Invalid URL format");
        }

        try {
            return Long.parseLong(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid product id");
        }
    }
}
