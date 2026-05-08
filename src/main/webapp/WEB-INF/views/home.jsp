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

<form method="post" action="/auth/logout">
    <button type="submit">
        Logout
    </button>
</form>

</body>
</html>
