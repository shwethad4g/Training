package com.example.Personal_Finance_Tracker_Final_Project.data_factory;



import com.example.Personal_Finance_Tracker_Final_Project.dto.DeleteAccountResponseDTO;

public class DeleteAccountServiceImplTestDataFactory {
   public static Long userId() {
        return 1L;
    }

    public static String token() {
        return "jwtToken123";
    }

    public static String successMessage() {
        return "Account successfully deleted";
    }

    public static DeleteAccountResponseDTO deleteAccountResponseDTO() {
        DeleteAccountResponseDTO dto = new DeleteAccountResponseDTO();
        dto.setMessage(successMessage());
        return dto;
    }
}