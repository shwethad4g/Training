package com.example.WeekendTaskJul27.service;


public interface CartService {
    void addToCart(int customerId, long productId, int quantity);
    void viewCart(int customerId);
}
