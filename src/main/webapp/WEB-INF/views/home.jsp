<%@ taglib
        prefix="c"
        uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: omar-ehab
  Date: 5/7/26
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<h1>
    Welcome, ${firstName}
</h1>

<p>
    You are successfully logged in.
</p>

<table>
    <tr><th>ID</th><th>Name</th><th>Price</th><th>Action</th></tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.stockQuantity}</td>
            <td>
                <a href="${pageContext.request.contextPath}/products?id=${p.id}">View Details</a>
            </td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="/auth/logout">
    <button type="submit">
        Logout
    </button>
</form>

</body>
</html>
