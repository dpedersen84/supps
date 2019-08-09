package com.dp.supps.data;

import com.dp.supps.data.CategoryDaoDB.CategoryMapper;
import com.dp.supps.data.GoalDaoDB.GoalMapper;
import com.dp.supps.entities.Category;
import com.dp.supps.entities.Goal;
import com.dp.supps.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        final String sql = "SELECT * FROM product";

        List<Product> products = jdbc.query(sql, new ProductMapper());

        addGoalAndCategoryToProducts(products);

        return products;
    }

    @Override
    public Product findByProductId(int productId) {
        try {
            final String sql = "SELECT * FROM product WHERE productId = ?";
            Product product = jdbc.queryForObject(sql, new ProductMapper(), productId);
            product.setCategory(getCategoryForProduct(product.getProductId()));
            product.setGoal(getGoalForProduct(product.getProductId()));
            return product;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Product addProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProductById(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            prod.setDescription(rs.getString("description"));
            prod.setPrice(rs.getBigDecimal("price"));

            return prod;
        }
    }
}
