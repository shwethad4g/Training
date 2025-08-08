package com.example.Ecommerce_Mini_Project.service;



import com.example.Ecommerce_Mini_Project.dto.OrderRequestDTO;
import com.example.Ecommerce_Mini_Project.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO request);
    List<OrderResponseDTO> getOrderHistory(Long userId);
}
