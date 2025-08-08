package com.example.Ecommerce_Mini_Project.controller;



import com.example.Ecommerce_Mini_Project.dto.CartItemDTO;
import com.example.Ecommerce_Mini_Project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody CartItemDTO cartItemDTO) {
        return cartService.addProductToCart(cartItemDTO);
    }

    @GetMapping("/{userId}")
    public List<CartItemDTO> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItemsByUserId(userId);
    }
}
