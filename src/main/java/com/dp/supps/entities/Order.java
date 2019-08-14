package com.dp.supps.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.orderId;
        hash = 37 * hash + Objects.hashCode(this.totalPrice);
        hash = 37 * hash + Objects.hashCode(this.orderDate);
        hash = 37 * hash + this.userId;
        hash = 37 * hash + Objects.hashCode(this.products);
        hash = 37 * hash + (this.orderSent ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.orderSent != other.orderSent) {
            return false;
        }
        if (!Objects.equals(this.totalPrice, other.totalPrice)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.products, other.products)) {
            return false;
        }
        return true;
    }
}