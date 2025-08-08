package com.example.Ecommerce_Mini_Project.service.impl;


import com.example.Ecommerce_Mini_Project.dto.ProductDTO;
import com.example.Ecommerce_Mini_Project.mapper.ProductMapper;
import com.example.Ecommerce_Mini_Project.model.Product;
import com.example.Ecommerce_Mini_Project.repository.ProductRepository;
import com.example.Ecommerce_Mini_Project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        productRepo.save(product);
    }

}
