/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.data;

import com.dp.supps.entities.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dpede
 */
@Repository
public class CategoryDaoDB implements CategoryDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Category getCategoryById(int id) {
        try {
            final String sql = "SELECT * FROM category "
                    + "WHERE id = ?";

            return jdbc.queryForObject(sql,
                    new CategoryMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        final String sql = "SELECT * FROM category";
        
        return jdbc.query(sql, new CategoryMapper());
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
        final String sql = "INSERT INTO category (name) VALUES (?)";
        
        jdbc.update(sql, category.getName());

//        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()",
//                Integer.class);
//
//        category.setId(newId);

        return category;
    }
    
    @Override
    @Transactional
    public void deleteCategoryById(int id) {
        final String deleteProduct = "DELETE FROM product WHERE categoryId = ?";
        jdbc.update(deleteProduct, id);
        
        final String deleteCategory = "DELETE FROM category WHERE id = ?";
        jdbc.update(deleteCategory, id);
    }

    public static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int index) throws SQLException {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));

            return category;
        }
    }

}
