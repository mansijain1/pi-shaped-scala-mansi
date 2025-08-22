package com.example.order_processing.model;


import java.io.Serializable;

public class Order implements Serializable {
    private String orderId;
    private String item;
    private int quantity;

    // Constructors
    public Order() {}

    public Order(String orderId, String item, int quantity) {
        this.orderId = orderId;
        this.item = item;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
