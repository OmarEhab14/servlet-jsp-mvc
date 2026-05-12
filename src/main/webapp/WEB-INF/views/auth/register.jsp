<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
  <div class="form-wrapper">
    <h2>Create Account</h2>

    <form method="post" action="${pageContext.request.contextPath}/auth/register">

      <div class="form-group">
        <input
                type="text"
                name="firstName"
                value="${oldDto.firstName()}"
                placeholder="First Name"
                class="form-input"
        />
        <c:if test="${error.errors['firstName'] != null}">
          <span class="field-error">${error.errors['firstName']}</span>
        </c:if>
      </div>

      <div class="form-group">
        <input
                type="text"
                name="lastName"
                value="${oldDto.lastName()}"
                placeholder="Last Name"
                class="form-input"
        />
        <c:if test="${error.errors['lastName'] != null}">
          <span class="field-error">${error.errors['lastName']}</span>
        </c:if>
      </div>

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
        Register
      </button>
    </form>

    <p class="form-footer">
      Already have an account?
      <a href="${pageContext.request.contextPath}/auth/login">Login here</a>
    </p>
  </div>
</div>
</body>
</html>