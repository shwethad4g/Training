package com.example.Ecommerce_Mini_Project.controller;



import com.example.Ecommerce_Mini_Project.dto.ProductDTO;
import com.example.Ecommerce_Mini_Project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return "Product added successfully";
    }

}
