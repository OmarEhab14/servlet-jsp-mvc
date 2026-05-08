<%--
  Created by IntelliJ IDEA.
  User: omar-ehab
  Date: 5/7/26
  Time: 11:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<form method="post" action="/auth/register">

  <input
          type="text"
          name="firstName"
          value="${oldDto.firstName()}"
          placeholder="First Name"
  />

  <c:if test="${error.errors['firstName'] != null}">
    <span>${error.errors['firstName']}</span>
  </c:if>

  <br>

  <input
          type="text"
          name="lastName"
          value="${oldDto.lastName()}"
          placeholder="Last Name"
  />

  <c:if test="${error.errors['lastName'] != null}">
    <span>${error.errors['lastName']}</span>
  </c:if>

  <br>

  <input
          type="email"
          name="email"
          value="${oldDto.email()}"
          placeholder="Email"
  />

  <c:if test="${error.errors['email'] != null}">
    <span>${error.errors['email']}</span>
  </c:if>

  <br>

  <input
          type="password"
          name="password"
          placeholder="Password"
  />

  <c:if test="${error.errors['password'] != null}">
    <span>${error.errors['password']}</span>
  </c:if>

  <br>

  <button type="submit">
    Register
  </button>

</form>

<p>
  Already have an account?
  <a href="${pageContext.request.contextPath}/auth/login">
    Login here
  </a>
</p>
</html>