package com.example.WeekendTaskJul27.repository;


import com.example.WeekendTaskJul27.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerCustomerId(int customerId);

}
