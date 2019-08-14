package com.dp.supps.data;

import com.dp.supps.data.CategoryDaoDB.CategoryMapper;
import com.dp.supps.data.GoalDaoDB.GoalMapper;
import com.dp.supps.entities.Category;
import com.dp.supps.entities.Goal;
import com.dp.supps.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoDB implements ProductDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Product> getAllProducts() {
        final String sql = "SELECT * FROM product ORDER BY productid ASC";

        List<Product> products = jdbc.query(sql, new ProductMapper());

        addGoalAndCategoryToProducts(products);

        return products;
    }

    @Override
    public List<Product> getProductsByGoalId(int goalId) {
        final String sql = "SELECT * FROM product WHERE goalId = ?";

        List<Product> products = jdbc.query(sql, new ProductMapper(), goalId);

        addGoalAndCategoryToProducts(products);

        return products;
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        final String sql = "SELECT * FROM product WHERE categoryId = ?";

        List<Product> products = jdbc.query(sql, new ProductMapper(), categoryId);

        addGoalAndCategoryToProducts(products);

        return products;
    }

    @Override
    public Product getProductById(int productId) {
        try {
            final String sql = "SELECT * FROM product WHERE productId = ?";
            Product product = jdbc.queryForObject(sql, new ProductMapper(), productId);

            if (product != null) {
                product.setCategory(getCategoryForProduct(product.getProductId()));
                product.setGoal(getGoalForProduct(product.getProductId()));
            }

            return product;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        final String sql = "INSERT INTO product (name, price, inventory,"
                + " goalId, categoryId) VALUES (?, ?, ?, ?, ?)"
                + " RETURNING productId";

        int id = jdbc.queryForObject(sql, new Object[]{product.getName(), product.getPrice(),
            product.getInventory(), product.getGoal().getId(),
            product.getCategory().getId()}, Integer.class);

        product.setProductId(id);

        return product;
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        final String sql = "UPDATE product SET name = ?, inventory = ?, goalId = ?, categoryId = ? WHERE productId = ?";

        jdbc.update(sql, product.getName(), product.getInventory(),
                product.getGoal().getId(), product.getCategory().getId(),
                product.getProductId());
    }

    @Override
    @Transactional
    public void deleteProductById(int productId) {
        // delete from orderproduct table 
        final String deleteFromOrderProduct = "DELETE FROM orderproduct WHERE productId = ?";
        jdbc.update(deleteFromOrderProduct, productId);

        // delete from review table
        final String deleteReview = "DELETE FROM review WHERE productId = ?";
        jdbc.update(deleteReview, productId);

        final String sql = "DELETE FROM product WHERE productId = ?";
        jdbc.update(sql, productId);
    }

    private void addGoalAndCategoryToProducts(List<Product> products) {
        for (Product product : products) {
            product.setGoal(getGoalForProduct(product.getProductId()));
            product.setCategory(getCategoryForProduct(product.getProductId()));
        }
    }

    private Goal getGoalForProduct(int productId) {
        final String sql = "SELECT g.* FROM goal g JOIN product p"
                + " ON p.goalId = g.id WHERE p.productId = ?";

        return jdbc.queryForObject(sql, new GoalMapper(), productId);
    }

    private Category getCategoryForProduct(int productId) {
        final String sql = "SELECT c.* FROM category c JOIN product p"
                + " ON p.categoryId = c.id WHERE p.productId = ?";

        return jdbc.queryForObject(sql, new CategoryMapper(), productId);
    }

    public static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int index) throws SQLException {
            Product prod = new Product();
            prod.setProductId(rs.getInt("productId"));
            prod.setName(rs.getString("name"));
            prod.setInventory(rs.getInt("inventory"));
            prod.setHeadline(rs.getString("headline"));
            prod.setPrice(rs.getBigDecimal("price"));
            prod.setImage(rs.getString("image"));

            return prod;
        }
    }
}
