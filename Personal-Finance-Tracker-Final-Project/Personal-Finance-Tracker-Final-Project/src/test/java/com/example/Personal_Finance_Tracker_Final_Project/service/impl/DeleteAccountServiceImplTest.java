package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.DeleteAccountDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.DeleteAccountServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.DeleteAccountResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.DeleteAccountResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DeleteAccountServiceImplTest {

    @Mock
    private DeleteAccountDAO deleteAccountDAO;

    @Mock
    private DeleteAccountResponseMapper responseMapper;

    @InjectMocks
    private DeleteAccountServiceImpl deleteAccountService;

    private Long userId;
    private String token;
    private DeleteAccountResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        userId = DeleteAccountServiceImplTestDataFactory.userId();
        token = DeleteAccountServiceImplTestDataFactory.token();
        responseDTO = DeleteAccountServiceImplTestDataFactory.deleteAccountResponseDTO();
    }

    @Test
    void testDeleteAccount_whenValidInput_thenReturnDeleteAccountResponseDTO() {
        Mockito.doNothing().when(deleteAccountDAO).assertUserExists(userId);
        Mockito.doNothing().when(deleteAccountDAO).blacklistTokenSafe(token, userId);
        Mockito.doNothing().when(deleteAccountDAO).deleteUserGraph(userId);
        Mockito.when(responseMapper.toDTO(DeleteAccountServiceImplTestDataFactory.
                successMessage())).thenReturn(responseDTO);

        DeleteAccountResponseDTO result = deleteAccountService.deleteAccount(userId, token);

        assertNotNull(result);
        assertEquals(responseDTO.getMessage(), result.getMessage());
    }
}