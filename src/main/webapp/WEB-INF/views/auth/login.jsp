<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
  <div class="form-wrapper">
    <h2>Login</h2>

    <form method="post" action="${pageContext.request.contextPath}/auth/login">
      <c:if test="${error.errors['login'] != null}">
        <div class="error-message">
            ${error.errors['login']}
        </div>
      </c:if>

      <div class="form-group">
        <input
                type="email"
                name="email"
                value="${oldDto.email()}"
                placeholder="Email"
                class="form-input"
        />
        <c:if test="${error.errors['email'] != null}">
          <span class="field-error">${error.errors['email']}</span>
        </c:if>
      </div>

      <div class="form-group">
        <input
                type="password"
                name="password"
                placeholder="Password"
                class="form-input"
        />
        <c:if test="${error.errors['password'] != null}">
          <span class="field-error">${error.errors['password']}</span>
        </c:if>
      </div>

      <button type="submit" class="btn btn-primary">
        Login
      </button>
    </form>

    <p class="form-footer">
      Don't have an account?
      <a href="${pageContext.request.contextPath}/auth/register">Register here</a>
    </p>
  </div>
</div>
</body>
</html>