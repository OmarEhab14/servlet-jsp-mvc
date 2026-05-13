package com.advprog.servletecommerce.infrastructure.controllers;

import com.advprog.servletecommerce.application.service.ProductService;
import com.advprog.servletecommerce.application.service.UserService;
import com.advprog.servletecommerce.application.service.impl.ProductServiceImpl;
import com.advprog.servletecommerce.application.service.impl.UserServiceImpl;
import com.advprog.servletecommerce.domain.dto.ProductDto;
import com.advprog.servletecommerce.domain.entities.User;
import com.advprog.servletecommerce.domain.enums.Role;
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
        productService = (ProductServiceImpl)  getServletContext().getAttribute("productService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attr = req.getAttribute("userId");

        if (attr == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login");
            return;
        }

        Long userId = ((Number) attr).longValue();
        User user = userService.getUserById(userId);

        if (user.getRole() == Role.USER) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        List<ProductDto> products = productService.getAllProducts();

        req.setAttribute("firstName", user.getFirstName());
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);
    }
}
