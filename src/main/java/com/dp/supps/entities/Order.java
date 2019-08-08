/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dp.supps.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author dpede
 */
//@Entity
//@Table(name = "\"order\"")
public class Order {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
    private int id;

//    @Column(nullable = false)
    private BigDecimal totalPrice;

//    @Column(nullable = false)
    private LocalDate orderDate;

//    @Column(nullable = false)
    private int userId;

//    @ManyToMany
//    @JoinTable(
//            name = "orderproduct",
//            joinColumns = {@JoinColumn(name = "orderid")},
//            inverseJoinColumns = {@JoinColumn(name = "productid")})
    
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
}
