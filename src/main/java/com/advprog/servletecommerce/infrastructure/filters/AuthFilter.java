package com.advprog.servletecommerce.infrastructure.filters;

import com.advprog.servletecommerce.application.exceptions.UnauthorizedException;
import com.advprog.servletecommerce.application.security.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import redis.clients.jedis.RedisClient;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private RedisClient redisClient;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        redisClient = (RedisClient) filterConfig.getServletContext()
                .getAttribute("redisClient");
    }
    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/auth/login",
            "/auth/register"
    );
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        if (PUBLIC_PATHS.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        if (path.startsWith("/css/") ||
                path.startsWith("/js/") ||
                path.startsWith("/images/") ||
                path.endsWith(".css") ||
                path.endsWith(".js") ||
                path.endsWith(".png") ||
                path.endsWith(".jpg") ||
                path.endsWith(".ico")) {
            chain.doFilter(request, response);
            return;
        }

        String sessionId = null;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SESSION_ID")) {
                    sessionId = cookie.getValue();
                }
            }
        }

        if (sessionId != null) {
            String userIdStr = redisClient.get("session: " + sessionId);
            if (userIdStr != null) {
                redisClient.expire("session: " + sessionId, 3600);
                Long userId = Long.parseLong(userIdStr);
                request.setAttribute("userId", userId);
                chain.doFilter(request, response);
                return;
            }
        }

//        HttpSession httpSession = httpRequest.getSession(false);
//        if (httpSession != null && httpSession.getAttribute("userId") != null) {
//            chain.doFilter(request, response);
//            return;
//        }

        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (JwtUtil.validateToken(token)) {
                Claims claims = JwtUtil.getClaims(token);
                httpRequest.setAttribute("userId", claims.get("userId"));
                httpRequest.setAttribute("role", claims.get("role"));
                chain.doFilter(request, response);
                return;
            }
        }
        throw new UnauthorizedException();
    }
}
