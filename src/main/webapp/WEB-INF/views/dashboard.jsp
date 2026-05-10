<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="navbar">
    <div class="navbar-content">
        <h3>E-Commerce Admin</h3>
        <div class="navbar-user">
            <span>${firstName}</span>
            <form method="post" action="${pageContext.request.contextPath}/auth/logout" class="logout-form">
                <button type="submit" class="btn btn-secondary">
                    Logout
                </button>
            </form>
        </div>
    </div>
</div>

<div class="main-content">
    <div class="content-wrapper">
        <div class="dashboard-header">
            <h1>Admin Dashboard</h1>
            <p>Manage your products, orders, and reviews</p>
        </div>

        <div class="dashboard-grid">
            <div class="dashboard-card">
                <h3>Products</h3>
                <p class="dashboard-stat">0</p>
                <a href="#" class="card-link">Manage Products →</a>
            </div>

            <div class="dashboard-card">
                <h3>Users</h3>
                <p class="dashboard-stat">0</p>
                <a href="#" class="card-link">Manage Users →</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>