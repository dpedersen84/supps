package com.dp.supps.service;

import com.dp.supps.data.OrderDaoDB;
import com.dp.supps.entities.Order;
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

        return orderDao.getAllOrders();
    }
}
