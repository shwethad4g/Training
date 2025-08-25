package com.example.Personal_Finance_Tracker_Final_Project.data_factory;



import com.example.Personal_Finance_Tracker_Final_Project.dto.LogoutResponseDTO;

public class LogoutServiceImplTestDataFactory {

    public static Long userId;
    public static String token;
    public static LogoutResponseDTO logoutResponseDTO;

    static {
        userId = 1L;
        token = "sampleToken";
        logoutResponseDTO = new LogoutResponseDTO();
        logoutResponseDTO.setMessage("Successfully logged out");
    }
}
