<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <p>Browse our available products below.</p>
        </div>

        <div class="section">
            <h2>Available Products</h2>

            <c:if test="${empty products}">
                <p class="empty-message">No products available at the moment.</p>
            </c:if>

            <div class="products-grid">
                <c:forEach var="p" items="${products}">
                    <div class="product-card">
                        <div class="product-info">
                            <h3 class="product-name">${p.name()}</h3>
                            <p class="product-price">$${p.price()}</p>
                            <p class="product-stock">
                                <c:choose>
                                    <c:when test="${p.stockQuantity() > 0}">
                                        <span class="stock-available">In Stock (${p.stockQuantity()})</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="stock-unavailable">Out of Stock</span>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                        <div class="product-actions">
                            <a href="${pageContext.request.contextPath}/products?id=${p.id()}" class="btn btn-view">
                                View Details
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>