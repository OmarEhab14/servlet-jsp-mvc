package com.advprog.servletecommerce.infrastructure.dao;

import com.advprog.servletecommerce.application.exceptions.InternalServerErrorException;
import com.advprog.servletecommerce.domain.dao.ReviewDao;
import com.advprog.servletecommerce.domain.entities.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ReviewDaoImpl implements ReviewDao {
    private final DataSource dataSource;

    @Override
    public Review save(Review review) {
        String query = "INSERT INTO reviews (user_id, product_id, rating, comment) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, review.getUserId());
            stmt.setLong(2, review.getProductId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating review failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    review.setId(generatedKeys.getLong(1));
                }
            }
            return review;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Review> findByProductId(Long productId) {
        String query = " SELECT * FROM reviews WHERE product_id = ? ORDER BY created_at DESC ";

        List<Review> reviews = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, productId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reviews.add(mapResultSetToReview(rs));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Something went wrong when trying to find reviews", e);
        }

        return reviews;
    }

    @Override
    public boolean existsByUserAndProduct(Long userId, Long productId) {
        String query = "SELECT 1 FROM reviews WHERE user_id = ? AND product_id = ? LIMIT 1";


        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setLong(1, userId);
            stmt.setLong(2, productId);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); /// return true if exist , else return false
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getAverageRating(Long productId) {
        String query = "SELECT avg(rating) FROM reviews WHERE product_id = ? LIMIT 1";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, productId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return 0;


    }

    ///  helper method
    private Review mapResultSetToReview(ResultSet rs) throws SQLException {
        return new Review(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("product_id"),
                rs.getInt("rating"),
                rs.getString("comment"),
                rs.getTimestamp("created_at").toLocalDateTime()

        );
    }
}
