package com.example.Personal_Finance_Tracker_Final_Project.data_factory;


import com.example.Personal_Finance_Tracker_Final_Project.model.User;

public class CustomUserDetailsServiceTestDataFactory {

    public static User user;

    static {
        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
        user.setPassword("secret");
        user.setEnabled(true);
    }
}
