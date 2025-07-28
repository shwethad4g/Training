package com.example.WeekendTaskJul27.repository;

import com.example.WeekendTaskJul27.model.Cart;
import com.example.WeekendTaskJul27.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);

    Optional<Cart> findByCustomerCustomerId(int customerId);
}
