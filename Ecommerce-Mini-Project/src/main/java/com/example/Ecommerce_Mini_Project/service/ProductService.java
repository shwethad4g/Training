package com.example.Ecommerce_Mini_Project.service;



import com.example.Ecommerce_Mini_Project.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    void addProduct(ProductDTO productDTO);
}
