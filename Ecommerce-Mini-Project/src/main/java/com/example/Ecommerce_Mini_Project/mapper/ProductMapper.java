package com.example.Ecommerce_Mini_Project.mapper;


import com.example.Ecommerce_Mini_Project.dto.ProductDTO;
import com.example.Ecommerce_Mini_Project.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}
