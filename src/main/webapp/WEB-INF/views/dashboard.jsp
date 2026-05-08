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
<form method="post" action="/auth/logout">
    <button type="submit">
        Logout
    </button>
</form>
</body>
</html>
