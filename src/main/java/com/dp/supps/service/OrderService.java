package com.dp.supps.service;

import com.dp.supps.data.OrderDaoDB;
import com.dp.supps.entities.Order;
import com.dp.supps.entities.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderDaoDB orderDao;

    @Autowired
    public OrderService(OrderDaoDB orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> findAll() {
        List<Order> orders = orderDao.getAllOrders();
        return orders;
    }

    public List<Order> findSent() {
        return orderDao.getSentOrders();
    }

    public List<Order> findUnsent() {
        return orderDao.getUnsentOrders();
    }

    public Order findUnsentOrderByUserId(int userId) {
        Order order = orderDao.getUnsentOrderByUserId(userId);

        if (order != null) {
            // calculate total price
            List<Product> products = order.getProducts();

            if (products == null || products.isEmpty()) {
                order.setTotalPrice(new BigDecimal("0"));
            }

            if (products.size() > 0) {
                BigDecimal totalPrice = new BigDecimal("0");
                for (Product product : products) {
                    BigDecimal price = product.getPrice();
                    totalPrice = totalPrice.add(price);
                }
                order.setTotalPrice(totalPrice);
            }
            return order;
        } else {
            Order newOrder = new Order();
            newOrder.setUserId(userId);
            return createOrder(newOrder);
        }
    }

    public Order findOrderByOrderId(int orderId) {
        return orderDao.getOrderById(orderId);
    }

    public void addToOrderProduct(int orderId, int productId) {
        orderDao.addToOrderProduct(orderId, productId);
    }

    public void deleteFromOrderProduct(int orderId, int productId) {
        orderDao.removeFromOrderProduct(orderId, productId);
    }

    public Order completeOrder(Order order) {
        // set orderSent to true
        order.setOrderSent(true);

        return orderDao.completeOrder(order);
    }

    public void deleteOrderById(int orderId) {
        orderDao.deleteOrderById(orderId);
    }

    public Order createOrder(Order order) {
        // set order id
        List<Order> orders = findAll();

        Order last = orders.get(orders.size() - 1);

        order.setOrderId(last.getOrderId() + 1);

        // set order date
        order.setOrderDate(LocalDate.now());

        return orderDao.createOrder(order);
    }
}
