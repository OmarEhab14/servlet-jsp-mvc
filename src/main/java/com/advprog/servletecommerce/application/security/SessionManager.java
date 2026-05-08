package com.advprog.servletecommerce.application.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.RedisClient;
import redis.clients.jedis.params.SetParams;

import java.util.UUID;

public class SessionManager {
    public static void attachSession(HttpServletResponse response, RedisClient redisClient, Long userId) {
        String sessionId = UUID.randomUUID().toString();
        redisClient.set("session: " + sessionId,
                String.valueOf(userId),
                SetParams.setParams().ex(3600));

        Cookie sessionCookie = new Cookie("SESSION_ID", sessionId);
        sessionCookie.setMaxAge(3600);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
    }
}
