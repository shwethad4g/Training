package com.example.Ecommerce_Mini_Project.dao;



import com.example.Ecommerce_Mini_Project.model.User;
import com.example.Ecommerce_Mini_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private UserRepository userRepository;


    public void save(User user) {
        userRepository.save(user);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
