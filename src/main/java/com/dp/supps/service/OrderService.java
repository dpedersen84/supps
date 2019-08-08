/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.service;

import com.dp.supps.data.OrderDaoDB;
import com.dp.supps.data.OrderRepository;
import com.dp.supps.entities.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dpede
 */
@Service
public class OrderService {
    
//    private final OrderRepository orderRepo;
    private final OrderDaoDB orderDao;
    
    @Autowired
    public OrderService(OrderDaoDB orderDao) {
//        this.orderRepo = orderRepo;
        this.orderDao = orderDao;
    }
    
    public List<Order> findAll() {
//        return orderRepo.findAll();

        return orderDao.getAllOrders();
    }
    
//    public Order findById(int id) {
//        return orderRepo.findById(id).orElse(null);
//    }
//    
//    public Order addOrder(Order p) {
//        return orderRepo.save(p);
//    }
//    
//    public void deleteById(int id) {
//        orderRepo.deleteById(id);
//    }
}
