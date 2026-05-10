<%@ taglib
        prefix="c"
        uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: omar-ehab
  Date: 5/8/26
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h1>Admin Dashboard</h1>
<h2>Welcome, ${firstName}</h2>
<table>
    <tr><th>ID</th><th>Name</th><th>Price</th><th>Action</th></tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id()}</td>
            <td>${p.name()}</td>
            <td>${p.price()}</td>
            <td>${p.stockQuantity()}</td>
            <td>
                    <%-- DELETE uses a mini form so it's a POST --%>
                <form action="${pageContext.request.contextPath}/products" method="post">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id"     value="${p.id()}"/>
                    <button type="submit">Delete</button>
                </form>
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
