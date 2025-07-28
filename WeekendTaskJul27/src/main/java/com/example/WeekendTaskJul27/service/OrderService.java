package com.example.WeekendTaskJul27.service;


import com.example.WeekendTaskJul27.model.Order;

import java.util.List;

public interface OrderService {
    void checkout(int customerId);
    List<Order> getOrdersByCustomerId(int customerId);
    void printOrdersForCustomer(int customerId);
}
