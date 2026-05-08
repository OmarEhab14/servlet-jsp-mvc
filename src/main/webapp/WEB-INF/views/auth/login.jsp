<%--
  Created by IntelliJ IDEA.
  User: omar-ehab
  Date: 5/7/26
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<form method="post" action="/auth/login">
  <c:if test="${error.errors['login'] != null}">
    <div style="color: red; margin-bottom: 10px;">
        ${error.errors['login']}
    </div>
  </c:if>
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
    Login
  </button>

</form>

<p>
  Don't have an account?
  <a href="${pageContext.request.contextPath}/auth/register">
    Register here
  </a>
</p>
</html>