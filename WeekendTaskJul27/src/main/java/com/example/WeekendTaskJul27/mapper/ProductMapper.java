package com.example.WeekendTaskJul27.mapper;

import com.example.WeekendTaskJul27.dto.ProductDto;
import com.example.WeekendTaskJul27.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDTO(Product product);
    List<ProductDto> toDtoList(List<Product> products);
}
