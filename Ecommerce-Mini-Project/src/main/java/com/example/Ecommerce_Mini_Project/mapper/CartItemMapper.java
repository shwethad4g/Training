package com.example.Ecommerce_Mini_Project.mapper;

import com.example.Ecommerce_Mini_Project.dto.CartItemDTO;
import com.example.Ecommerce_Mini_Project.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "productPrice")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(expression = "java(cartItem.getProduct().getPrice() * cartItem.getQuantity())", target = "totalPrice")
    CartItemDTO toDto(CartItem cartItem);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    CartItem toEntity(CartItemDTO dto);
}
