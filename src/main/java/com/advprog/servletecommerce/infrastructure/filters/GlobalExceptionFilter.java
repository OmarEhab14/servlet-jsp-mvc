package com.advprog.servletecommerce.infrastructure.filters;

import com.advprog.servletecommerce.application.exceptions.AppException;
import com.advprog.servletecommerce.application.exceptions.UnauthorizedException;
import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.application.exceptions.dtos.ExceptionResponse;
import com.advprog.servletecommerce.application.mappers.ExceptionMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@WebFilter("/*")
public class GlobalExceptionFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        try {

            chain.doFilter(request, response);

        } catch (ValidationException e) {
            throw e;
        } catch (UnauthorizedException e) {
            resp.sendRedirect("/auth/login");
        } catch (AppException e) {

            ExceptionResponse error = ExceptionMapper.toExceptionResponse(e);

            resp.setStatus(error.status());

            req.setAttribute("error", error);

            req.getRequestDispatcher("/WEB-INF/views/errors/error.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionResponse error = ExceptionResponse.builder()
                            .status(500)
                            .title("Internal Server Error")
                            .detail(e.getMessage())
                            .timeStamp(Instant.now())
                            .errors(Map.of())
                            .build();

            resp.setStatus(500);

            req.setAttribute("error", error);

            req.getRequestDispatcher("/WEB-INF/views/errors/error.jsp")
                    .forward(req, resp);
        }
    }
}
