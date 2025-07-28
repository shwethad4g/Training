package com.example.WeekendTaskJul27.service;


import com.example.WeekendTaskJul27.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer login(String email, String password);
    Customer createCustomer(Customer customer);
    Optional<Customer> getCustomerById(int id);

}
