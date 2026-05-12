<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.name} - Product Details</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="navbar">
    <div class="navbar-content">
        <h3>E-Commerce</h3>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-back">
            ← Back to Home
        </a>
    </div>
</div>

<div class="main-content">
    <div class="content-wrapper">

        <div class="section product-detail-section">
            <div class="product-detail-container">
                <div class="product-image-wrapper">
                    <c:choose>
                        <c:when test="${not empty product.imageUrl}">
                            <img src="${product.imageUrl}"
                                 alt="${product.name}"
                                 class="product-image"
                        </c:when>
                        <c:otherwise>
                            <div class="product-image-placeholder">
                                <span>No Image Available</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="product-detail-info">
                    <div class="product-detail-header">
                        <h1 class="product-detail-title">${product.name}</h1>
                        <p class="product-detail-price">$${product.price}</p>
                    </div>

                    <p class="product-detail-description">${product.description}</p>

                    <p class="product-detail-stock">
                        <c:choose>
                            <c:when test="${product.stockQuantity > 0}">
                                <span class="stock-badge stock-available">
                                    ✓ In Stock (${product.stockQuantity} available)
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="stock-badge stock-unavailable">
                                    ✗ Out of Stock
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Customer Reviews</h2>

            <c:if test="${empty reviews}">
                <p class="empty-message">No reviews yet. Be the first to review this product!</p>
            </c:if>

            <div class="reviews-container">
                <c:forEach var="r" items="${reviews}">
                    <div class="review-card">
                        <div class="review-header">
                            <div class="review-rating">
                                <c:forEach begin="1" end="${r.rating}">
                                    <span class="star-filled">★</span>
                                </c:forEach>
                                <c:forEach begin="${r.rating + 1}" end="5">
                                    <span class="star-empty">☆</span>
                                </c:forEach>
                                <span class="rating-number">${r.rating}/5</span>
                            </div>
                            <small class="review-date">${r.createdAt}</small>
                        </div>
                        <p class="review-comment">${r.comment}</p>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="section">
            <h2>Write a Review</h2>

            <form method="post" action="${pageContext.request.contextPath}/reviews" class="review-form">
                <input type="hidden" name="productId" value="${product.id}" />

                <div class="form-group rating-form">
                    <label for="rating">Rating</label>
                    <div class="rating-input">
                        <input type="number"
                               id="rating"
                               name="rating"
                               min="1"
                               max="5"
                               class="form-input rating-number-input"
                               placeholder="1-5"
                               required />
                        <span class="rating-helper">out of 5 stars</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="comment" class="reviews-label">Your Review</label>
                    <textarea id="comment"
                              name="comment"
                              class="form-textarea rating-textarea"
                              rows="5"
                              placeholder="Share your experience with this product..."
                              required></textarea>
                </div>

                <button type="submit" class="btn btn-primary">
                    Submit Review
                </button>
            </form>
        </div>

    </div>
</div>
</body>
</html>