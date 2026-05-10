<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${product.name()} - Details</title>
</head>
<body>

<!-- Navigation -->
<a href="${pageContext.request.contextPath}/home">← Back to Products</a>

<!-- Product Details -->
<div class="product-container">

    <img
            src="${product.imageUrl()}"
            alt="${product.name()}"
            width="300"
            style="border-radius: 10px;"
    />

    <h1>${product.name()}</h1>

    <h2>Price: $${product.price()}</h2>

    <p>
        <strong>Status:</strong>

        <c:choose>
            <c:when test="${product.stockQuantity() > 0}">
                In Stock (${product.stockQuantity()} available)
            </c:when>

            <c:otherwise>
                <span style="color: red;">Out of Stock</span>
            </c:otherwise>
        </c:choose>
    </p>

    <hr>

    <h3>Description</h3>

    <p>${product.description()}</p>

    <c:if test="${product.stockQuantity() > 0}">
        <form action="${pageContext.request.contextPath}/cart/add" method="post">

            <input
                    type="hidden"
                    name="productId"
                    value="${product.id()}"
            >

            <button type="submit">
                Add to Cart
            </button>

        </form>
    </c:if>

</div>

</body>
</html>