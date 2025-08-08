package com.example.Ecommerce_Mini_Project.controller;

import com.example.Ecommerce_Mini_Project.dto.OrderRequestDTO;
import com.example.Ecommerce_Mini_Project.dto.OrderResponseDTO;
import com.example.Ecommerce_Mini_Project.model.Order;
import com.example.Ecommerce_Mini_Project.repository.OrderItemRepository;
import com.example.Ecommerce_Mini_Project.service.OrderPdfService;
import com.example.Ecommerce_Mini_Project.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemRepository orderRepository;

    @Autowired
    private OrderPdfService orderPDFService;

    @PostMapping("/place")
    public OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.placeOrder(orderRequestDTO);
    }

    @GetMapping("/history/{userId}")
    public List<OrderResponseDTO> getOrderHistory(@PathVariable Long userId) {
        return orderService.getOrderHistory(userId);
    }

    @GetMapping("/{orderId}/download")
    public void downloadOrderInvoice(@PathVariable Long orderId, HttpServletResponse response) throws IOException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId)).getOrder();

        response.setContentType("application/pdf");
        String headerValue = "attachment; filename=order_" + orderId + "_invoice.pdf";
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        orderPDFService.exportOrderToPDF(response, order);
    }

}
