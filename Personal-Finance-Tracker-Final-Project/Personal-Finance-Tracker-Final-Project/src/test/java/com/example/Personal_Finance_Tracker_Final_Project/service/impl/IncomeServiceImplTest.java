package com.example.Personal_Finance_Tracker_Final_Project.service.impl;

import com.example.Personal_Finance_Tracker_Final_Project.dao.IncomeDAO;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.IncomeServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.IncomeDTO;
import com.example.Personal_Finance_Tracker_Final_Project.mapper.IncomeMapper;
import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import com.example.Personal_Finance_Tracker_Final_Project.model.User;
import com.example.Personal_Finance_Tracker_Final_Project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncomeServiceImplTest {

    @Mock
    private IncomeDAO incomeDAO;

    @Mock
    private IncomeMapper incomeMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IncomeServiceImpl incomeService;

    @Test
    void testCreateIncome_whenUserExists_thenReturnIncomeDTO() {
        IncomeDTO incomeDTO = IncomeServiceImplTestDataFactory.incomeDTO;
        User user = IncomeServiceImplTestDataFactory.user;
        Income income = IncomeServiceImplTestDataFactory.income;
        Income savedIncome = IncomeServiceImplTestDataFactory.savedIncome;
        IncomeDTO savedIncomeDTO = IncomeServiceImplTestDataFactory.savedIncomeDTO;

        when(userRepository.findByEmailIgnoreCase("testUser")).thenReturn(Optional.of(user));
        when(incomeMapper.toEntity(incomeDTO)).thenReturn(income);
        when(incomeDAO.saveIncome(income)).thenReturn(savedIncome);
        when(incomeMapper.toDTO(savedIncome)).thenReturn(savedIncomeDTO);

        IncomeDTO result = incomeService.createIncome(incomeDTO, "testUser");

        assertThat(result).isEqualTo(savedIncomeDTO);
    }

    @Test
    void testCreateIncome_whenUserNotFound_thenThrowException() {
        IncomeDTO incomeDTO = IncomeServiceImplTestDataFactory.incomeDTO;

        when(userRepository.findByEmailIgnoreCase("testUser")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> incomeService.createIncome(incomeDTO, "testUser"));
    }

    @Test
    void testUpdateIncome_whenIncomeExists_thenReturnUpdatedIncomeDTO() {
        IncomeDTO incomeDTO = IncomeServiceImplTestDataFactory.incomeDTO;
        Income income = IncomeServiceImplTestDataFactory.income;
        Income updatedIncome = IncomeServiceImplTestDataFactory.updatedIncome;
        IncomeDTO updatedIncomeDTO = IncomeServiceImplTestDataFactory.updatedIncomeDTO;

        when(incomeDAO.findByIdAndUserId(1L, 1L)).thenReturn(income);
        when(incomeDAO.saveIncome(income)).thenReturn(updatedIncome);
        when(incomeMapper.toDTO(updatedIncome)).thenReturn(updatedIncomeDTO);

        IncomeDTO result = incomeService.updateIncome(1L, incomeDTO, 1L);

        assertThat(result).isEqualTo(updatedIncomeDTO);
    }
}
