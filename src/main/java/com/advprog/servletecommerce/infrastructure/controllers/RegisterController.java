package com.advprog.servletecommerce.infrastructure.controllers;

import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.application.security.CookieManager;
import com.advprog.servletecommerce.application.security.SessionManager;
import com.advprog.servletecommerce.application.service.UserService;
import com.advprog.servletecommerce.domain.dto.AuthResponseDto;
import com.advprog.servletecommerce.domain.dto.RegisterRequestDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.RedisClient;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/auth/register")
public class RegisterController extends HttpServlet {
    private UserService userService;
    private RedisClient redisClient;
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
        redisClient = (RedisClient) getServletContext().getAttribute("redisClient");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RegisterRequestDto requestDto = new RegisterRequestDto(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("email"),
                request.getParameter("password")
        );
        try {
            AuthResponseDto responseDto = userService.register(requestDto);

            CookieManager.attachToken(response, responseDto.token());

            SessionManager.attachSession(response, redisClient, responseDto.id());

            response.sendRedirect("/home");
        } catch (ValidationException ex) {
            request.setAttribute("error", ex);
            request.setAttribute("oldDto", requestDto);

            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
                    .forward(request, response);
        }
    }

    public void destroy() {
    }
}