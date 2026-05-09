package com.advprog.servletecommerce.infrastructure.filters;

import com.advprog.servletecommerce.application.exceptions.RateLimitingException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.RedisClient;

import java.io.IOException;

@WebFilter("/*")
public class RateLimitingFilter implements Filter {

    private RedisClient redisClient;

    private static final int MAX_REQUESTS = 5;
    private static final int WINDOW_SECONDS = 10;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        redisClient = (RedisClient) filterConfig
                .getServletContext()
                .getAttribute("redisClient");
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        String identifier;

        Object userId = httpRequest.getAttribute("userId");

        if (userId != null) {

            identifier = "user:" + userId;

        } else {

            identifier = "ip:" + request.getRemoteAddr();
        }

        String key = "rate:" + identifier;

        int count = 0;

        String value = redisClient.get(key);

        if (value != null) {

            count = Integer.parseInt(value);
        }

        if (count >= MAX_REQUESTS) {

            throw new  RateLimitingException();

        }

        redisClient.incr(key);

        if (count == 0) {

            redisClient.expire(key, WINDOW_SECONDS);
        }

        chain.doFilter(request, response);
    }


}