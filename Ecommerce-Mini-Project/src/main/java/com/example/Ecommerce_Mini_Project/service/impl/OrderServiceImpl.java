package com.example.Ecommerce_Mini_Project.service.impl;

import com.example.Ecommerce_Mini_Project.dto.CartItemDTO;
import com.example.Ecommerce_Mini_Project.dto.OrderRequestDTO;
import com.example.Ecommerce_Mini_Project.dto.OrderResponseDTO;
import com.example.Ecommerce_Mini_Project.mapper.CartItemMapper;
import com.example.Ecommerce_Mini_Project.model.*;
import com.example.Ecommerce_Mini_Project.repository.*;
import com.example.Ecommerce_Mini_Project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public OrderResponseDTO placeOrder(OrderRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setAddress(request.getAddress());
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();

            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Product " + product.getName() + " is out of stock");
            }

            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRepository.save(product);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice() * cartItem.getQuantity());
            orderItems.add(orderItem);
            CartItemDTO cartItemDTO = cartItemMapper.toDto(cartItem);
            cartItemDTOList.add(cartItemDTO);
        }
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        cartItemRepository.deleteAll(cartItems);
        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(savedOrder.getId());
        response.setAddress(savedOrder.getAddress());
        response.setOrderDate(savedOrder.getOrderDate());
        response.setItems(cartItemDTOList);
        return response;
    }

    @Override
    public List<OrderResponseDTO> getOrderHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = orderRepository.findByUser(user);

        List<OrderResponseDTO> orderHistory = new ArrayList<>();

        for (Order order : orders) {
            OrderResponseDTO responseDTO = new OrderResponseDTO();
            responseDTO.setOrderId(order.getId());
            responseDTO.setAddress(order.getAddress());
            responseDTO.setOrderDate(order.getOrderDate());

            List<CartItemDTO> itemDTOs = new ArrayList<>();
            for (OrderItem item : order.getOrderItems()) {
                // Manually building CartItemDTO from OrderItem since we don’t have an OrderItemMapper
                CartItemDTO cartItemDTO = new CartItemDTO();
                cartItemDTO.setId(item.getId());
                cartItemDTO.setProductId(item.getProduct().getId());
                cartItemDTO.setProductName(item.getProduct().getName());
                cartItemDTO.setProductPrice(item.getProduct().getPrice());
                cartItemDTO.setQuantity(item.getQuantity());
                cartItemDTO.setTotalPrice(item.getPrice());
                cartItemDTO.setUserId(userId); // Set userId manually

                itemDTOs.add(cartItemDTO);
            }
            responseDTO.setItems(itemDTOs);
            orderHistory.add(responseDTO);
        }

        return orderHistory;
    }
}
