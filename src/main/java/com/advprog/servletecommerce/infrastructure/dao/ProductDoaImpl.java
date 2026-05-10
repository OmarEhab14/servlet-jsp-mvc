package com.advprog.servletecommerce.infrastructure.dao;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import com.advprog.servletecommerce.domain.dao.ProductDao;
import com.advprog.servletecommerce.domain.entities.Product;
import com.advprog.servletecommerce.domain.entities.ProductDetails;
import com.advprog.servletecommerce.domain.entities.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProductDoaImpl implements ProductDao {
    private final DataSource dataSource;

    @Override
    public Optional<ProductDetails> findById(Long id) {
//        String query="SELECT * FROM products WHERE id= ?";
//        String query = "SELECT * FROM products LEFT JOIN reviews ON product_id = reviews.product_id";
        String query = """
                    SELECT 
                        p.id as p_id, p.name, p.description, p.price, p.stock_quantity,p.image_url,
                        r.id as r_id, r.user_id, r.product_id, r.rating, r.comment, r.created_at
                        FROM products p
                        LEFT JOIN reviews r ON p.id = r.product_id
                        WHERE p.id = ?
                """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                ProductDetails productDetails = null;

                while (rs.next()) {

                    if (productDetails == null) {

                        productDetails = mapResultSetToProductDetails(rs, new ArrayList<>());
                    }


                    Review review = mapResultSetToReview(rs);
                    if (review != null) {
                        productDetails.getReviews().add(review);
                    }
                }

                return Optional.ofNullable(productDetails);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Something went wrong when trying to find Product with id: " + id, e);
        }
    }

    @Override
    public Product save(Product product) {
        String query = """
                INSERT INTO products (name, description, price, stock_quantity, image_url)
                VALUES
                (?, ?, ?, ?, ?)
                """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            stmt.setString(5, product.getImageUrl());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    long generatedId = rs.getLong(1);

                    product.setId(generatedId);

                    return product;
                }
            }
            throw new RuntimeException("Failed to retrieve generated ID");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Something went wrong when trying to save product", e);
        }
    }

    @Override
    public List<Product> findAll() {
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product p = mapResultSetToProduct(rs);
                    products.add(p);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Something went wrong when trying to find Products ", e);
        }
        return products;
    }

    @Override
    public boolean deleteById(Long id) {
        String deleteReviews = "DELETE FROM reviews WHERE product_id = ?";
        String deleteProduct = "DELETE FROM products WHERE id = ?";

        try (Connection conn = dataSource.getConnection()) {

            conn.setAutoCommit(false); // important: transaction

            try (PreparedStatement reviewStmt = conn.prepareStatement(deleteReviews)) {
                reviewStmt.setLong(1, id);
                reviewStmt.executeUpdate();
            }

            try (PreparedStatement productStmt = conn.prepareStatement(deleteProduct)) {
                productStmt.setLong(1, id);
                int affectedRows = productStmt.executeUpdate();

                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            log.error("Failed deleting product {} with reviews", id, e);
            return false;
        }
    }

    @Override
    public boolean existsById(Long id) {
        String query = "SELECT 1 FROM products WHERE id = ? LIMIT 1";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getString("image_url"),
                rs.getInt("stock_quantity")
        );
    }private ProductDetails mapResultSetToProductDetails(ResultSet rs, List<Review> reviews) throws SQLException {
        return new ProductDetails(
                rs.getLong("p_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("stock_quantity"),
                rs.getString("image_url"),
                reviews
        );
    }

    private Review mapResultSetToReview(ResultSet rs) throws SQLException {

        Long reviewId = rs.getLong("r_id");
        if (rs.wasNull()) {
            return null;
        }

        Timestamp ts = rs.getTimestamp("created_at");

        return new Review(
                reviewId,
                rs.getLong("user_id"),
                rs.getLong("product_id"),
                rs.getInt("rating"),
                rs.getString("comment"),
                ts != null ? ts.toLocalDateTime() : null
        );
    }
}
