package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.DeleteAccountResponseDTO;
import com.example.Personal_Finance_Tracker_Final_Project.service.DeleteAccountService;
import com.example.Personal_Finance_Tracker_Final_Project.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class DeleteAccountControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private DeleteAccountController controller;

    @Mock
    private DeleteAccountService deleteAccountService;

    @Mock
    private JwtUtil jwtUtil;

    private AutoCloseable mocks;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL = "/api/auth/account";
    private static final String TOKEN = "valid.jwt.token";
    private static final String HEADER = "Bearer " + TOKEN;
    private static final Long USER_ID = 42L;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        if (mocks != null) mocks.close();
    }

    @Test
    void deleteAccount_success_returns200AndMessage() throws Exception {

        when(jwtUtil.extractUserId(TOKEN)).thenReturn(USER_ID);

        DeleteAccountResponseDTO dto = new DeleteAccountResponseDTO();
        dto.setMessage("Account deleted successfully");

        when(deleteAccountService.deleteAccount(eq(USER_ID), eq(TOKEN)))
                .thenReturn(dto);

        String expectedJson = objectMapper.writeValueAsString(dto);


        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.delete(URL)
                        .header("Authorization", HEADER)
                        .accept(MediaType.APPLICATION_JSON)
        );


        Assertions.assertEquals(HttpStatus.OK.value(),
                result.andReturn().getResponse().getStatus());
        Assertions.assertEquals(expectedJson,
                result.andReturn().getResponse().getContentAsString());
    }

    @Test
    void deleteAccount_missingHeader_returns401() throws Exception {

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.delete(URL)
                        .accept(MediaType.APPLICATION_JSON)
        );


        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(),
                result.andReturn().getResponse().getStatus());
    }

    @Test
    void deleteAccount_invalidToken_returns401() throws Exception {

        when(jwtUtil.extractUserId(TOKEN)).thenReturn(null);


        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.delete(URL)
                        .header("Authorization", HEADER)
                        .accept(MediaType.APPLICATION_JSON)
        );


        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(),
                result.andReturn().getResponse().getStatus());
    }
}
