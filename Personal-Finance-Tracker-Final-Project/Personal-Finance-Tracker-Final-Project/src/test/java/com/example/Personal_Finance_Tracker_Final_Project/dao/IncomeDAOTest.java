package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import com.example.Personal_Finance_Tracker_Final_Project.repository.IncomeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncomeDAOTest {

    @Mock
    private IncomeRepository incomeRepository;

    @InjectMocks
    private IncomeDAO incomeDAO;

    @Test
    void testSaveIncome_whenValidIncome_thenReturnSavedIncome() {
        Income income = new Income();
        income.setId(1L);
        income.setDescription("Salary");
        income.setAmount(BigDecimal.valueOf(2500.0));

        when(incomeRepository.save(income)).thenReturn(income);

        Income result = incomeDAO.saveIncome(income);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDescription()).isEqualTo("Salary");
        assertThat(result.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(2500.0));
    }

    @Test
    void testFindByIdAndUserId_whenIncomeExists_thenReturnIncome() {
        Long incomeId = 10L;
        Long userId = 5L;

        Income income = new Income();
        income.setId(incomeId);

        when(incomeRepository.findByIdAndUserId(incomeId, userId)).thenReturn(Optional.of(income));

        Income result = incomeDAO.findByIdAndUserId(incomeId, userId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(incomeId);
    }

    @Test
    void testFindByIdAndUserId_whenIncomeNotFound_thenThrowException() {
        Long incomeId = 10L;
        Long userId = 5L;

        when(incomeRepository.findByIdAndUserId(incomeId, userId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> incomeDAO.findByIdAndUserId(incomeId, userId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Income not found or not owned by user");
    }
}
