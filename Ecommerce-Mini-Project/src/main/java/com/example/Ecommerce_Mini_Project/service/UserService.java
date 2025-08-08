package com.example.Ecommerce_Mini_Project.service;



import com.example.Ecommerce_Mini_Project.model.User;

public interface UserService {
    User findByUsername(String username);
    void saveUser(User user);
}
