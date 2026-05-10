package com.advprog.servletecommerce.infrastructure.controllers;

import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.application.service.ReviewService;
import com.advprog.servletecommerce.domain.dto.ReviewRequestDto;
import com.advprog.servletecommerce.domain.entities.Review;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/reviews/*")
public class ReviewController extends HttpServlet {
    private ReviewService reviewService;
    @Override
    public void init() {

        reviewService = (ReviewService) getServletContext().getAttribute("reviewService");    }

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

            response.sendRedirect(request.getContextPath() + "/products/" + productId);
        }
        catch (ValidationException e) {
            request.setAttribute(
                    "error",
                    e.getMessage());

            request.getRequestDispatcher(
                            "/WEB-INF/views/product-detail.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

                // /reviews/product/{id}
                Long productId = extractProductId(request);
                List<Review> reviews = reviewService.getProductReviews(productId);

                request.setAttribute("reviews", reviews);
                /// GUI of review
                request.getRequestDispatcher("/WEB-INF/views/product-detail.jsp").forward(request, response);
    }

    private Long extractProductId(HttpServletRequest request) {

        String path = request.getPathInfo();

        log.info("Extract product id from path: {}", path);
        if (path == null) {
            throw new IllegalArgumentException("Missing path");
        }

        String[] parts = path.split("/");

        //  /product/{id}
        if (parts.length < 3 || !"product".equals(parts[1])) {
            throw new IllegalArgumentException("Invalid URL format");
        }

        try {
            log.info("parts[2] from path: {}", parts[2]);

            return Long.parseLong(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid product id");
        }
    }
}
