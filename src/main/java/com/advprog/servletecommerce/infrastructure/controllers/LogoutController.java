package com.advprog.servletecommerce.infrastructure.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.RedisClient;

import java.io.IOException;

@WebServlet("/auth/logout")
public class LogoutController extends HttpServlet {
    private RedisClient redisClient;

    @Override
    public void init() throws ServletException {
        redisClient = (RedisClient) getServletContext()
                .getAttribute("redisClient");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCookies() != null) {
            for (var cookie : req.getCookies()) {
                if (cookie.getName().equals("SESSION_ID")) {
                    String sessionId = cookie.getValue();
                    redisClient.del("session: " + sessionId);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setValue("");
                    resp.addCookie(cookie);
                }
            }
        }
        resp.sendRedirect("/auth/login");
    }
}
