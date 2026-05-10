package com.advprog.servletecommerce.infrastructure.controllers;


import com.advprog.servletecommerce.application.service.ProductService;
import com.advprog.servletecommerce.application.service.UserService;
import com.advprog.servletecommerce.application.service.impl.ProductServiceImpl;
import com.advprog.servletecommerce.application.service.impl.UserServiceImpl;
import com.advprog.servletecommerce.domain.dto.ProductDto;
import com.advprog.servletecommerce.domain.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    private UserService userService;
    private ProductService productService;
    @Override
    public void init() throws ServletException {
        userService = (UserServiceImpl) getServletContext()
                .getAttribute("userService");
        productService = (ProductServiceImpl) getServletContext()
                .getAttribute("productService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getAttribute("userId");
        User user = userService.getUserById(userId);
        req.setAttribute("firstName", user.getFirstName());
        List<ProductDto> products = productService.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);
    }
}
