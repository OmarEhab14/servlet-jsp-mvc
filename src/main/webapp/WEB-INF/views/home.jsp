<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="navbar">
    <div class="navbar-content">
        <h3>E-Commerce</h3>
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
        <div class="welcome-message">
            <h1>Welcome back, ${firstName}!</h1>
            <p>You are successfully logged in.</p>
        </div>

        <!-- Products section will go here -->
        <div class="section">
            <h2>Products</h2>
            <div class="products-grid">
                <!-- Product items will be added here dynamically -->
            </div>
        </div>

        <!-- Reviews section will go here -->
        <div class="section">
            <h2>Recent Reviews</h2>
            <div class="reviews-list">
                <!-- Review items will be added here dynamically -->
            </div>
        </div>
    </div>
</div>
</body>
</html>