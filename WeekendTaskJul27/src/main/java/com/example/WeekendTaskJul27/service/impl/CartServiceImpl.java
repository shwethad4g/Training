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
import java.util.Optional;

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

    public String viewCart(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> cartItems = cartItemRepository.findByCart_CartId(cart.getCartId());

        if (cartItems.isEmpty()) {
            return " Your cart is empty.";
        }

        StringBuilder sb = new StringBuilder(" Cart Items:\n");
        double total = 0;

        for (CartItem item : cartItems) {
            double itemTotal = item.getProduct().getPrice() * item.getQuantity();
            total += itemTotal;

            sb.append("Product: ").append(item.getProduct().getName())
                    .append(" | Qty: ").append(item.getQuantity())
                    .append(" | Price: ₹").append(item.getProduct().getPrice())
                    .append(" | Subtotal: ₹").append(itemTotal)
                    .append("\n");
        }
        sb.append("Total: ₹").append(total);
        return sb.toString();
    }

    @Override
    public void addToCart(int customerId, int productId, int quantity) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Product product = productRepository.findById((long) productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartRepository.save(newCart);
                });


        Optional<CartItem> existingItemOpt = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setCustomer(customer);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }

    }
}
