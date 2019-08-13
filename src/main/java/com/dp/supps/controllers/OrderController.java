package com.dp.supps.controllers;

import com.dp.supps.entities.Order;
import com.dp.supps.service.OrderService;
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

@RestController
public class OrderController {

    private final OrderService orderServ;

    @Autowired
    public OrderController(OrderService orderServ) {
        this.orderServ = orderServ;
    }

    @GetMapping("/api/orders")
    public List<Order> getOrders() {
        List<Order> orders = orderServ.findAll();

        return orders;
    }

    @GetMapping("/api/orders/sent")
    public List<Order> getSentOrders() {
        List<Order> orders = orderServ.findSent();

        return orders;
    }

    @GetMapping("/api/orders/unsent")
    public List<Order> getUnsentOrders() {
        List<Order> orders = orderServ.findUnsent();

        return orders;
    }

    @GetMapping("/api/orders/unsent/{userId}")
    public ResponseEntity<Order> findUnsentOrderByUserId(@PathVariable int userId) {
        Order result = orderServ.findUnsentOrderByUserId(userId);

        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/orders/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable int id) {
        Order result = orderServ.findOrderByOrderId(id);

        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        return orderServ.createOrder(order);
    }
    
    @PostMapping("/api/orders/sent")
    @ResponseStatus(HttpStatus.CREATED)
    public Order sendOrder(@RequestBody Order order) {
        return orderServ.completeOrder(order);
    }

    @DeleteMapping("/api/orders/{id}")
    public void delete(@PathVariable int id) {
        orderServ.deleteOrderById(id);
    }
    
    @PostMapping("/api/orders/{orderId}/{productId}")
    public void addProductToOrder(@PathVariable int orderId, @PathVariable int productId) {
        orderServ.addToOrderProduct(orderId, productId);
    }

    @DeleteMapping("/api/orders/{orderId}/{productId}")
    public void deleteProductFromOrder(@PathVariable int orderId, @PathVariable int productId) {
        orderServ.deleteFromOrderProduct(orderId, productId);
    }
}
