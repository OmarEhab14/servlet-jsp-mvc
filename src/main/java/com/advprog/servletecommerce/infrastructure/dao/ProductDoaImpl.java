package com.advprog.servletecommerce.infrastructure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.advprog.servletecommerce.domain.dao.ProductDao;
import com.advprog.servletecommerce.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProductDoaImpl implements ProductDao {
    private final DataSource dataSource;
    @Override
    public Optional<Product> findById(Long id) {
        String query="SELECT * FROM products WHERE id= ?";
        try(
            Connection connection= dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)
        ){
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToProduct(rs));
                }
            }
        }catch (SQLException e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("Something went wrong when trying to find Product with id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        String query="INSERT INTO products (name, description, price, stock_quantity)\n" +
                "VALUES\n" +
                "(?, ?, ?, ?)";
        try(
                Connection connection= dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)
        ){
            stmt.setString(1, product.getName());
            stmt.setString(2,product.getDescription());
            stmt.setDouble(3,product.getPrice());
            stmt.setInt(4,product.getStockQuantity());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    long generatedId = rs.getLong(1);

                    product.setId(generatedId);

                    return product;
                }
            }
            throw new RuntimeException("Failed to retrieve generated ID");
        }catch (SQLException e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("Something went wrong when trying to save product", e);
        }
    }

    @Override
    public List<Product> findAll() {
        String query="SELECT * FROM products";
        List<Product> products=new ArrayList<>();
        try(
                Connection connection= dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)
        ){
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product p=mapResultSetToProduct(rs);
                    products.add(p);
                }
            }
            return products;
        }catch (SQLException e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("Something went wrong when trying to find Products ", e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE FROM products WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("stockQuantity")
        );
    }
}
