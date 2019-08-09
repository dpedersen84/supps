/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.data;

import com.dp.supps.entities.Order;
import java.util.List;

/**
 *
 * @author dpede
 */
public interface OrderDao {
    
    List<Order> getAllOrders();
    
    Order getOrderById(int orderId);
    
    Order addOrder(Order order);
    
    boolean deleteOrderById(int orderId);
}
