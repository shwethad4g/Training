package com.example.Personal_Finance_Tracker_Final_Project.controller;

import com.example.Personal_Finance_Tracker_Final_Project.dto.ExpenseCategoryDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import com.example.Personal_Finance_Tracker_Final_Project.service.ExpenseCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExpenseCategoryControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ExpenseCategoryController expenseCategoryController;

    @Mock
    private ExpenseCategoryService categoryService;

    @Mock
    private UserRepository userRepository;

    private static final String CATEGORY_REQUEST_BODY = "" +
            "{\"name\":\"Groceries\",\"description\":\"Food and household items\"}";
    private static final String CATEGORY_RESPONSE_BODY =
            "{\"id\":1,\"name\":\"Groceries\",\"description\":\"Food and household items\"}";

    private ObjectMapper objectMapper = new ObjectMapper();
    private User testUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(expenseCategoryController).build();


        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("test@example.com");


        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("test@example.com")
                .password("password")
                .roles("USER")
                .build();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
        );
    }

    @Test
    public void testCreateCategory_Success() throws Exception {

        ExpenseCategoryDTO createdCategory = new ExpenseCategoryDTO();
        createdCategory.setName("Groceries");


        when(userRepository.findByEmailIgnoreCase(anyString()))
                .thenReturn(Optional.of(testUser));
        when(categoryService.createCategory(any(ExpenseCategoryDTO.class), anyLong()))
                .thenReturn(createdCategory);


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CATEGORY_REQUEST_BODY));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Groceries"));

    }


    @Test
    public void testCreateCategory_EmptyRequestBody() throws Exception {

        when(userRepository.findByEmailIgnoreCase(anyString()))
                .thenReturn(Optional.of(testUser));


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));

        result.andExpect(status().isOk());
    }

    @Test
    public void testCreateCategory_MalformedJSON() throws Exception {

        when(userRepository.findByEmailIgnoreCase(anyString()))
                .thenReturn(Optional.of(testUser));


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{invalid json}"));

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateCategory_NullValues() throws Exception {

        ExpenseCategoryDTO createdCategory = new ExpenseCategoryDTO();
        createdCategory.setName(null);


        when(userRepository.findByEmailIgnoreCase(anyString()))
                .thenReturn(Optional.of(testUser));
        when(categoryService.createCategory(any(ExpenseCategoryDTO.class), anyLong()))
                .thenReturn(createdCategory);

        String nullCategoryRequest = "{\"name\":null}";

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nullCategoryRequest));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").isEmpty());

    }


}