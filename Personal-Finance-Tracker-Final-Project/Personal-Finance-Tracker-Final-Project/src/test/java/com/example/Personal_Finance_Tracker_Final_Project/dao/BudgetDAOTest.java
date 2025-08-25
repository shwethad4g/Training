package com.example.Personal_Finance_Tracker_Final_Project.dao;


import com.example.Personal_Finance_Tracker_Final_Project.data_factory.BudgetDAOTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.data_factory.BudgetServiceImplTestDataFactory;
import com.example.Personal_Finance_Tracker_Final_Project.dto.BudgetDTO;
import com.example.Personal_Finance_Tracker_Final_Project.model.Budget;
import com.example.Personal_Finance_Tracker_Final_Project.repository.BudgetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BudgetDAOTest {

    @Mock
    private BudgetRepository budgetRepository;

    @InjectMocks
    private BudgetDAO budgetDAO;

    @Test
    void testSaveBudget_whenValidBudgetDTO_thenReturnSavedBudget() {
        BudgetDTO dto = BudgetDAOTestDataFactory.budgetDTO;

        Budget budget = new Budget();
        budget.setCategory(dto.getCategory());
        budget.setAmount(dto.getAmount());
        budget.setYear(dto.getYear());
        budget.setMonth(dto.getMonth());

        when(budgetRepository.save(any(Budget.class))).thenReturn(budget);

        Budget result = budgetDAO.saveBudget(budget);

        assertThat(result).isNotNull();
        assertThat(result.getCategory()).isEqualTo("Food");
        assertThat(result.getAmount()).isEqualTo(500.0);
        assertThat(result.getYear()).isEqualTo(2025);
        assertThat(result.getMonth()).isEqualTo(8);
    }
}
