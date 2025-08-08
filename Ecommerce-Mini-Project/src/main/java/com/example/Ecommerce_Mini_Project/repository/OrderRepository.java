package com.example.Ecommerce_Mini_Project.repository;


import com.example.Ecommerce_Mini_Project.model.Order;
import com.example.Ecommerce_Mini_Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByUser(User user);

}
