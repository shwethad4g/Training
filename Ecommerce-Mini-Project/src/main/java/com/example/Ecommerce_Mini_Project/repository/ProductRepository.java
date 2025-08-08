package com.example.Ecommerce_Mini_Project.repository;


import com.example.Ecommerce_Mini_Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
