package com.example.WeekendTaskJul27.service;


public interface CartService {

    String viewCart(int customerId);
    void addToCart(int customerId, int productId, int quantity);
}
