/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.data;

import com.dp.supps.data.ProductDaoDB.ProductMapper;
import com.dp.supps.entities.Order;
import com.dp.supps.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dpede
 */
@Repository
public class OrderDaoDB implements OrderDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Order> getAllOrders() {
        final String sql = "SELECT * FROM order";
        
        List<Order> orders = jdbc.query(sql, new OrderMapper());
        
        addProductsToOrders(orders);
        
        return orders;
    }

    @Override
    public Order getOrderById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteOrderById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addProductsToOrders(List<Order> orders) {
        for (Order order : orders) {
            order.setProducts(getProductsForOrder(order.getId()));
        }
    }
    
    private List<Product> getProductsForOrder(int id) {
        final String sql = "SELECT p.name, p.price FROM product p JOIN orderproduct op ON op.productid = p.id WHERE op.orderid = ?";
        return jdbc.query(sql, new ProductMapper(), id);
    }
    
    public static final class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int index) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setOrderDate(rs.getDate("orderDate").toLocalDate());
            order.setUserId(rs.getInt("userId"));
            order.setTotalPrice(rs.getBigDecimal("totalPrice"));
            return order;
        }
    }
    
}
