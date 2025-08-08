package com.example.Ecommerce_Mini_Project.service;


import com.example.Ecommerce_Mini_Project.dto.CartItemDTO;

import java.util.List;

public interface CartService {
    String addProductToCart(CartItemDTO cartItemDTO);

    List<CartItemDTO> getCartItemsByUserId(Long userId);

}
