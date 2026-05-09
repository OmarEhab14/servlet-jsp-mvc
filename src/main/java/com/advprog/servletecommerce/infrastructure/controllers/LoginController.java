package com.advprog.servletecommerce.infrastructure.controllers;

import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.application.security.SessionManager;
import com.advprog.servletecommerce.application.service.UserService;
import com.advprog.servletecommerce.configs.AppConfig;
import com.advprog.servletecommerce.domain.dto.AuthResponseDto;
import com.advprog.servletecommerce.domain.dto.LoginRequestDto;
import com.advprog.servletecommerce.domain.enums.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.RedisClient;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {
    private UserService userService;
    private RedisClient redisClient;

    @Override
    public void init() {
        userService = AppConfig.getUserService();
        redisClient = AppConfig.getRedisClient();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        LoginRequestDto dto = new LoginRequestDto(
                req.getParameter("email"),
                req.getParameter("password")
        );

        try {
            AuthResponseDto responseDto = userService.login(dto);

            SessionManager.attachSession(resp, redisClient, responseDto.id());

            Role userRole = responseDto.role();

            if (userRole.equals(Role.ADMIN)) {
                resp.sendRedirect("/dashboard");
                return;
            }

            resp.sendRedirect("/home");

        } catch (ValidationException e) {
            req.setAttribute("error", e);
            req.setAttribute("oldDto", dto);

            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp")
                    .forward(req, resp);
        }
    }
}