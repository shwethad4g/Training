package com.example.WeekendTaskJul27.controller;

import com.example.WeekendTaskJul27.dto.ProductDto;
import com.example.WeekendTaskJul27.model.Customer;
import com.example.WeekendTaskJul27.service.CartService;
import com.example.WeekendTaskJul27.service.CustomerService;
import com.example.WeekendTaskJul27.service.OrderService;
import com.example.WeekendTaskJul27.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecommerce")
public class ProductController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;


    @PostMapping("/signup")
    public String signUp(@RequestBody Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return "Sign-up successful! Your Customer ID is: " + created.getCustomerId();
    }


    @PostMapping("/login")
    public String login(@RequestParam int customerId, @RequestParam String password) {
        Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);
        if (optionalCustomer.isEmpty()) {
            return "Customer not found. Please sign up first.";
        }

        Customer customer = optionalCustomer.get();
        if (!customer.getPassword().equals(password)) {
            return "Incorrect password. Access denied.";
        }

        return "Login successful. Welcome, " + customer.getName() + "!";
    }


    @GetMapping("/products")
    public List<ProductDto> viewProducts() {
        return productService.getAllProducts();
    }


    @PostMapping("/cart/add")
    public String addToCart(@RequestParam int customerId,
                            @RequestParam int productId,
                            @RequestParam int quantity) {
        cartService.addToCart(customerId, productId, quantity);
        return "Added to cart.";
    }

    @GetMapping("/cart/view")
    public String viewCart(@RequestParam int customerId) {
        return cartService.viewCart(customerId);
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam int customerId) {
        return orderService.checkout(customerId);
    }

    @GetMapping("/orders")
    public String viewOrders(@RequestParam int customerId) {
        return orderService.printOrdersForCustomer(customerId);
    }
}
