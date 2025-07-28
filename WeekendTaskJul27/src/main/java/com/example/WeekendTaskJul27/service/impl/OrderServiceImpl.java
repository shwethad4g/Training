package com.example.WeekendTaskJul27.service.impl;


import com.example.WeekendTaskJul27.model.*;
import com.example.WeekendTaskJul27.repository.*;
import com.example.WeekendTaskJul27.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private  CustomerRepository customerRepository;

    @Override
    @Transactional
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderRepository.findByCustomerCustomerId(customerId);
    }

    @Transactional
    public String checkout(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<CartItem> cartItems = cartItemRepository.findByCustomer(customer);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot checkout.");
        }

        double totalAmount = 0.0;

        for (CartItem item : cartItems) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setStatus("PLACED");
        order = orderRepository.save(order);

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());
            orderItemRepository.save(orderItem);
        }

        cartItemRepository.deleteAll(cartItems);

        return " Checkout complete!\nOrder ID: " + order.getOrderId() +
                "\nTotal Amount: ₹" + totalAmount +
                "\nOrder Status: " + order.getStatus();
    }



    @Transactional
    public String printOrdersForCustomer(int customerId) {
        List<Order> orders = orderRepository.findByCustomerCustomerId(customerId);

        if (orders.isEmpty()) {
            return "No orders found for Customer ID: " + customerId;
        }

        StringBuilder sb = new StringBuilder("Orders for Customer ID: " + customerId + ":\n");
        for (Order order : orders) {
            sb.append("Order ID: ").append(order.getOrderId())
                    .append(", Total: ₹").append(order.getTotalAmount())
                    .append(", Items: ").append(order.getItems().size())
                    .append(", Status: ").append(order.getStatus()).append("\n");
        }

        return sb.toString();
    }

}

