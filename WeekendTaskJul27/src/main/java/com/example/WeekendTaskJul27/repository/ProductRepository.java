package com.example.WeekendTaskJul27.repository;

import com.example.WeekendTaskJul27.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
