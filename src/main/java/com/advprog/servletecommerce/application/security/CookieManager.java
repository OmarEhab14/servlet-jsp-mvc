package com.advprog.servletecommerce.application.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
    public static void attachToken(HttpServletResponse response, String token) {
        Cookie tokenCookie = new Cookie("TOKEN", token);
        tokenCookie.setMaxAge(3600);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
    }
}
