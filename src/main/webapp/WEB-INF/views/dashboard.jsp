<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<div class="navbar">
    <div class="navbar-content">
        <h3>Admin Panel</h3>

        <div class="navbar-user">
            <span>${firstName}</span>

            <form method="post"
                  action="${pageContext.request.contextPath}/auth/logout"
                  class="logout-form">
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
            <h1>Product Management</h1>
            <p>Create, view, and delete products from your system.</p>
        </div>


        <div class="section">
            <h2>Add New Product</h2>

            <form method="post"
                  action="${pageContext.request.contextPath}/products"
                  class="product-form">

                <input type="hidden" name="action" value="create"/>

                <div class="form-grid">
                    <input class="form-input" type="text" name="name" placeholder="Product name" required/>
                    <input class="form-input" type="text" name="description" placeholder="Description" required/>
                    <input class="form-input" type="number" step="0.01" name="price" placeholder="Price" required/>
                    <input class="form-input" type="text" name="imageUrl" placeholder="Image URL"/>
                    <input class="form-input" type="number" name="stockQuantity" placeholder="Stock quantity" required/>
                </div>

                <button type="submit" class="btn btn-primary mt-10">
                    Create Product
                </button>
            </form>
        </div>


        <div class="section">
            <h2>All Products</h2>

            <c:if test="${empty products}">
                <p class="empty-message">No products available.</p>
            </c:if>

            <div class="products-grid">

                <c:forEach var="p" items="${products}">
                    <div class="product-card">

                        <div class="product-info">
                            <h3 class="product-name">${p.name()}</h3>

                            <p class="product-price">$${p.price()}</p>

                            <p class="product-stock">
                                Stock: ${p.stockQuantity()}
                            </p>
                        </div>

                        <div class="product-actions">

                            <a class="btn-view"
                               href="${pageContext.request.contextPath}/products?id=${p.id()}">
                                View
                            </a>

                            <form method="post"
                                  action="${pageContext.request.contextPath}/products"
                                  class="inline-form">

                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="id" value="${p.id()}"/>

                                <button type="submit" class="btn-delete">
                                    Delete
                                </button>
                            </form>

                        </div>

                    </div>
                </c:forEach>

            </div>
        </div>

    </div>
</div>

</body>
</html>