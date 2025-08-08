package com.example.Ecommerce_Mini_Project.repository;


import com.example.Ecommerce_Mini_Project.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
