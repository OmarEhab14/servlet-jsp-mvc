<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Products</title>
    <style>
        .product {
            border: 1px solid #ccc;
            padding: 15px;
            margin-bottom: 20px;
        }

        .review {
            margin-left: 20px;
            padding: 10px;
            border-left: 3px solid #007bff;
        }
    </style>
</head>

<body>

<h1>Products</h1>

<c:forEach var="product" items="${products}">

    <div class="product">

        <h2>${product.name}</h2>
        <p>${product.description}</p>

        <h3>Reviews:</h3>

        <c:if test="${empty product.reviews}">
            <p>No reviews yet.</p>
        </c:if>

        <c:forEach var="review" items="${product.reviews}">
            <div class="review">
                <strong>${review.userName}</strong><br/>
                ⭐ Rating: ${review.rating}<br/>
                <p>${review.comment}</p>
            </div>
        </c:forEach>

    </div>

</c:forEach>

</body>
</html>