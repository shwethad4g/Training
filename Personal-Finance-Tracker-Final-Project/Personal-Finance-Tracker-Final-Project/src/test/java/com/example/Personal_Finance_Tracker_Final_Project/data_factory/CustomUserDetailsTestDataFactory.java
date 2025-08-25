package com.example.Personal_Finance_Tracker_Final_Project.data_factory;



import com.example.Personal_Finance_Tracker_Final_Project.service.impl.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class CustomUserDetailsTestDataFactory {

    public static CustomUserDetails customUserDetails;

    static {
        customUserDetails = new CustomUserDetails(
                1L,
                "john",
                "secret",
                true,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
