package com.dp.supps.data;

import com.dp.supps.entities.Order;
import java.util.List;

public interface OrderDao {
    
    List<Order> getAllOrders();
    
    Order getOrderById(int orderId);
    
    Order addOrder(Order order);
    
    void deleteOrderById(int orderId);
}
