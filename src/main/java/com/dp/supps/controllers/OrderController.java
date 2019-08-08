/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.controllers;

import com.dp.supps.entities.Order;
import com.dp.supps.entities.Product;
import com.dp.supps.service.OrderService;
import com.dp.supps.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dpede
 */
@RestController
public class OrderController {
    
    private final OrderService orderServ;
    private final ProductService productServ;
    
    @Autowired
    public OrderController(OrderService orderServ, ProductService productServ) {
        this.orderServ = orderServ;
        this.productServ = productServ;
    }
    
    @GetMapping("/api/orders")
    public List<Order> getOrders() {
        List<Order> orders = orderServ.findAll();
        
        return orders;
    }
    
//    @GetMapping("/api/orders/{id}")
//    public ResponseEntity<Order> findOrderById(@PathVariable int id) {
//        Order result = orderServ.findById(id);
//
//        if (result == null) {
//            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(result);
//    }
//    
//    @PostMapping("/api/orders")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Order addOrder(@RequestBody Order order) {
//        List<Product> orderProducts = order.getProducts();
//        
//        for (Product p : orderProducts) {
//            productServ.findById(p.getId());
//        }
//        
//        
//        return orderServ.addOrder(order);
//    }
//    
//    @DeleteMapping("/api/orders/{id}")
//    public void delete(@PathVariable int id) {
//        orderServ.deleteById(id);
//    }
}
