package com.dp.supps.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order {

    private int orderId;

    private BigDecimal totalPrice;

    private LocalDate orderDate;

    private int userId;

    private List<Product> products;
    
    private boolean orderSent;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isOrderSent() {
        return orderSent;
    }

    public void setOrderSent(boolean orderSent) {
        this.orderSent = orderSent;
    }
    
}
