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
@WebServlet("/reviews")
public class ReviewController extends HttpServlet {

    private ReviewService reviewService;

    @Override
    public void init() {
        reviewService = (ReviewService) getServletContext().getAttribute("reviewService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long userId = (Long) request.getAttribute("userId");
        Long productId = Long.parseLong(request.getParameter("productId"));

        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        ReviewRequestDto dto = new ReviewRequestDto(userId, productId, rating, comment);

        reviewService.createReview(dto);

        response.sendRedirect(request.getContextPath() + "/products?id=" + productId);
    }
}
