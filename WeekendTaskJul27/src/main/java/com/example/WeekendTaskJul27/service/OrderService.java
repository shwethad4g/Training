package com.example.WeekendTaskJul27.service;


import com.example.WeekendTaskJul27.model.Order;

import java.util.List;

public interface OrderService {
    String checkout(int customerId);
    List<Order> getOrdersByCustomerId(int customerId);
    String printOrdersForCustomer(int customerId);
}
