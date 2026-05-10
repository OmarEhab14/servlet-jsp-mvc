package com.advprog.servletecommerce.infrastructure.controllers;

import com.advprog.servletecommerce.application.mappers.ProductMapper;
import com.advprog.servletecommerce.application.service.ProductService;
import com.advprog.servletecommerce.application.service.UserService;
import com.advprog.servletecommerce.application.service.impl.ProductServiceImpl;
import com.advprog.servletecommerce.application.service.impl.UserServiceImpl;
import com.advprog.servletecommerce.domain.dto.ProductDto;
import com.advprog.servletecommerce.domain.entities.Product;
import com.advprog.servletecommerce.domain.entities.User;
import com.advprog.servletecommerce.domain.enums.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {
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
        Role role = user.getRole();
        List<ProductDto> products = productService.getAllProducts();
        req.setAttribute("products",products);
        if (role == Role.ADMIN) {
            req.getRequestDispatcher(req.getContextPath() + "/dashboard").forward(req,resp);
            return;
        }
        req.setAttribute("firstName", user.getFirstName());
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}
