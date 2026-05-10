package com.advprog.servletecommerce.infrastructure.controllers;

import java.io.IOException;

import com.advprog.servletecommerce.application.enums.HttpStatus;
import com.advprog.servletecommerce.application.exceptions.AppException;
import com.advprog.servletecommerce.application.exceptions.InternalServerErrorException;
import com.advprog.servletecommerce.application.exceptions.InvalidProductIdException;
import com.advprog.servletecommerce.application.mappers.ProductMapper;
import com.advprog.servletecommerce.application.service.ProductService;
import com.advprog.servletecommerce.application.service.impl.ProductServiceImpl;
import com.advprog.servletecommerce.domain.dto.ProductDetailsDto;
import com.advprog.servletecommerce.domain.entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductServiceImpl) getServletContext()
                .getAttribute("productService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required.");
            return;
        }

        try {
            Long productId = Long.parseLong(idParam);
            ProductDetailsDto product = productService.getProduct(productId);

            req.setAttribute("product", product);
            req.getRequestDispatcher("/WEB-INF/views/product-detail.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            throw new InvalidProductIdException(idParam);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("create")) {
            Product product = new Product();

            product.setName(req.getParameter("name"));
            product.setDescription(req.getParameter("description"));
            product.setPrice(
                    Double.parseDouble(req.getParameter("price"))
            );
            product.setImageUrl(req.getParameter("imageUrl"));
            product.setStockQuantity(
                    Integer.parseInt(req.getParameter("stockQuantity"))
            );

            productService.createProduct(product);
        }
        else if(action.equals("delete")){
            Long id= Long.parseLong(req.getParameter("id"));
            productService.deleteProduct(id);
        }
        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }

}
