package com.shopkart.data;

import java.util.HashMap;
import java.util.Map;

public class OrderBuilder {

    private int cartId;
    private String address = "Trivandrum, India";

    private OrderBuilder() {
    }

    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    public OrderBuilder withCartId(int cartId) {
        this.cartId = cartId;
        return this;
    }

    public OrderBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public Map<String, Object> build() {
        Map<String, Object> body = new HashMap<>();
        body.put("cartId", cartId);
        body.put("address", address);
        return body;
    }
}