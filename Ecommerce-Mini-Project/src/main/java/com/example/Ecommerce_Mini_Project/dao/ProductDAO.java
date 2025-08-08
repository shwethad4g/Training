package com.example.Ecommerce_Mini_Project.dao;


import com.example.Ecommerce_Mini_Project.model.Product;
import com.example.Ecommerce_Mini_Project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
