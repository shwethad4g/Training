package com.example.Ecommerce_Mini_Project.service;



import com.example.Ecommerce_Mini_Project.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}

