/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.data;

import com.dp.supps.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dpede
 */
@Repository
public class ProductDaoDB {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int index) throws SQLException {
            Product prod = new Product();
            prod.setId(rs.getInt("id"));
            prod.setName(rs.getString("name"));
            prod.setInventory(rs.getInt("inventory"));
            prod.setDescription(rs.getString("description"));
            prod.setPrice(rs.getBigDecimal("price"));

            return prod;
        }
    }
}
