package com.spring.demo;

public class Orders {
    private String orderName;
    private String address;

    public Orders (String orderName, String address) {
        this.orderName = orderName;
        this.address = address;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getAddress() {
        return address;
    }
}
