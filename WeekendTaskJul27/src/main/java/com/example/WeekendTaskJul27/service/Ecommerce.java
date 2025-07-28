package com.example.WeekendTaskJul27.service;

import com.example.WeekendTaskJul27.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Ecommerce {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    private int customerId = -1;

    public void startShopping() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to  S-Fashion Boutique! ");

        System.out.print("Are you a new customer? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        Customer customer;

        if (response.equals("yes")) {

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Create a password: ");
            String password = scanner.nextLine();

            Customer newCustomer = new Customer();
            newCustomer.setName(name);
            newCustomer.setEmail(email);
            newCustomer.setPassword(password);

            customer = customerService.createCustomer(newCustomer);
            customerId = customer.getCustomerId();

            System.out.println("Sign-up successful! Your Customer ID is: " + customerId);
        } else {

            System.out.print("Enter Customer ID: ");
            customerId = Integer.parseInt(scanner.nextLine());

            Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);
            if (optionalCustomer.isEmpty()) {
                System.out.println("Customer not found. Please sign up first.");
                return;
            }

            customer = optionalCustomer.get();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (!password.equals(customer.getPassword())) {
                System.out.println("Incorrect password. Access denied.");
                return;
            }

            System.out.println("Login successful. Welcome, " + customer.getName() + "!");
        }

        // MENU
        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. View Orders");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n--- Product List ---");
                    productService.getAllProducts().forEach(product ->
                            System.out.println(product.getProductId() + ". " + product.getName()
                                    + " - ₹" + product.getPrice()
                                    + " (Stock: " + product.getStockQuantity() + ")"));
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    cartService.addToCart(customerId, productId, quantity);
                    System.out.println("Added to cart.");
                    break;

                case 3:
                    System.out.println("\n--- Cart ---");
                    cartService.viewCart(customerId);
                    break;

                case 4:
                    System.out.println("\n--- Checkout ---");
                    orderService.checkout(customerId);
                    System.out.println("Checkout complete.");
                    break;

                case 5:
                    System.out.println("\n--- Orders ---");
                    orderService.printOrdersForCustomer(customerId);
                    break;

                case 6:
                    System.out.println("Exiting... Thank you!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
