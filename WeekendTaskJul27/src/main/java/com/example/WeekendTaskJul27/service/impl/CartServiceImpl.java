package com.example.WeekendTaskJul27.service.impl;


import com.example.WeekendTaskJul27.model.Cart;
import com.example.WeekendTaskJul27.model.CartItem;
import com.example.WeekendTaskJul27.model.Customer;
import com.example.WeekendTaskJul27.model.Product;
import com.example.WeekendTaskJul27.repository.CartItemRepository;
import com.example.WeekendTaskJul27.repository.CartRepository;
import com.example.WeekendTaskJul27.repository.CustomerRepository;
import com.example.WeekendTaskJul27.repository.ProductRepository;
import com.example.WeekendTaskJul27.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public void addToCart(int customerId, long productId, int quantity) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCustomer(customer);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        cartItemRepository.save(cartItem);
    }

    public void viewCart(int customerId) {
        List<CartItem> cartItems = cartItemRepository.findByCustomer_CustomerId(customerId);

        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n--- Cart ---");
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            System.out.println(product.getName() + " x" + item.getQuantity() +
                    " = ₹" + (product.getPrice() * item.getQuantity()));
        }
    }


}
