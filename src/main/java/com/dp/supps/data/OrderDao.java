package com.dp.supps.data;

import com.dp.supps.entities.Order;
import java.util.List;

public interface OrderDao {
    
    List<Order> getAllOrders();
    
    List<Order> getSentOrders();
    
    List<Order> getUnsentOrders();
    
    Order getUnsentOrderByUserId(int userId);
    
    Order getOrderById(int orderId);
    
    Order completeOrder(Order order);
    
    Order createOrder(Order order);
    
    void deleteOrderById(int orderId);
}
