package com.dp.supps.data;

import com.dp.supps.data.ProductDaoDB.ProductMapper;
import com.dp.supps.entities.Order;
import com.dp.supps.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoDB implements OrderDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Order> getAllOrders() {
        final String sql = "SELECT * FROM orders";

        List<Order> orders = jdbc.query(sql, new OrderMapper());

        addProductsToOrders(orders);

        return orders;
    }

    @Override
    public List<Order> getSentOrders() {
        final String sql = "SELECT * FROM orders WHERE orderSent = true";

        List<Order> orders = jdbc.query(sql, new OrderMapper());

        addProductsToOrders(orders);

        return orders;
    }

    @Override
    public List<Order> getUnsentOrders() {
        final String sql = "SELECT * FROM orders WHERE orderSent = false";

        List<Order> orders = jdbc.query(sql, new OrderMapper());

        addProductsToOrders(orders);

        return orders;
    }

    @Override
    public Order getUnsentOrderByUserId(int userId) {
        final String select = "SELECT * FROM orders WHERE userId = ? AND orderSent = false";

        try {
            Order order = jdbc.queryForObject(select, new OrderMapper(), userId);

            order.setProducts(getProductsForOrder(order.getOrderId()));

            return order;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        final String sql = "SELECT * FROM orders WHERE orderId = ?";

        Order order = jdbc.queryForObject(sql, new OrderMapper(), orderId);

        order.setProducts(getProductsForOrder(order.getOrderId()));

        return order;
    }

    @Override
    @Transactional
    public Order addOrder(Order order) {
        final String sql = "INSERT INTO orders (orderId, totalPrice, orderDate, userId, orderSent) VALUES (?, ?, ?, ?, ?)";

        jdbc.update(sql, order.getOrderId(), order.getTotalPrice(), order.getOrderDate(), order.getUserId(), order.isOrderSent());

        List<Order> orders = getAllOrders();

        insertIntoOrderProduct(order);

        return order;
    }

    @Override
    public Order createOrder(Order order) {
        final String sql = "INSERT INTO orders (orderId, totalPrice, orderDate, userId, orderSent) VALUES (?, ?, ?, ?, ?)";

        jdbc.update(sql, order.getOrderId(), order.getTotalPrice(), order.getOrderDate(), order.getUserId(), order.isOrderSent());

        return order;
    }

    @Override
    public void deleteOrderById(int orderId) {
        final String deleteFromOrderProduct = "DELETE FROM orderproduct WHERE orderId = ?";
        jdbc.update(deleteFromOrderProduct, orderId);

        final String deleteOrder = "DELETE FROM orders WHERE orderId = ?";
        jdbc.update(deleteOrder, orderId);
    }

    private void addProductsToOrders(List<Order> orders) {
        for (Order order : orders) {
            order.setProducts(getProductsForOrder(order.getOrderId()));
        }
    }

    private List<Product> getProductsForOrder(int orderId) {
        final String sql = "SELECT p.* FROM product p JOIN orderproduct op ON op.productid = p.productid WHERE op.orderid = ?";
        List<Product> products = jdbc.query(sql, new ProductMapper(), orderId);
        return products;
    }

    public void insertIntoOrderProduct(Order order) {
        final String sql = "INSERT INTO orderproduct (orderId, productId) VALUES (?, ?)";

        List<Product> products = order.getProducts();

        for (Product product : products) {
            jdbc.update(sql, order.getOrderId(), product.getProductId());
        }
    }

    public void addToOrderProduct(int orderId, int productId) {
        final String sql = "INSERT INTO orderproduct (orderId, productId) VALUES (?, ?)";

        jdbc.update(sql, orderId, productId);
    }

    public void removeFromOrderProduct(int orderId, int productId) {
        final String sql = "DELETE FROM orderproduct WHERE orderId = ? AND productId = ?";

        jdbc.update(sql, orderId, productId);
    }

    public static final class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int index) throws SQLException {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setOrderDate(rs.getDate("orderDate").toLocalDate());
            order.setUserId(rs.getInt("userId"));
            order.setTotalPrice(rs.getBigDecimal("totalPrice"));
            order.setOrderSent(rs.getBoolean("orderSent"));
            return order;
        }
    }

}
