package com.example.Ecommerce_Mini_Project.service.impl;


import com.example.Ecommerce_Mini_Project.dto.CartItemDTO;
import com.example.Ecommerce_Mini_Project.mapper.CartItemMapper;
import com.example.Ecommerce_Mini_Project.model.CartItem;
import com.example.Ecommerce_Mini_Project.model.Product;
import com.example.Ecommerce_Mini_Project.model.User;
import com.example.Ecommerce_Mini_Project.repository.CartItemRepository;
import com.example.Ecommerce_Mini_Project.repository.ProductRepository;
import com.example.Ecommerce_Mini_Project.repository.UserRepository;
import com.example.Ecommerce_Mini_Project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public String addProductToCart(CartItemDTO cartItemDTO) {

        User user = userRepository.findById(cartItemDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));


        if (product.getQuantity() < cartItemDTO.getQuantity()) {
            throw new RuntimeException("Not enough product quantity available");
        }


        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItem.setUser(user);
        cartItem.setProduct(product);


        cartItemRepository.save(cartItem);

        return "Product added to cart successfully!";
    }

    @Override
    public List<CartItemDTO> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
