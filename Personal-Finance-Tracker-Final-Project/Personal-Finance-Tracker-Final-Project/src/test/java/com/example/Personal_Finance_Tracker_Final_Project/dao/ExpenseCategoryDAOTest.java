package com.example.Personal_Finance_Tracker_Final_Project.dao;

import com.example.Personal_Finance_Tracker_Final_Project.model.ExpenseCategory;
import com.example.Personal_Finance_Tracker_Final_Project.repository.ExpenseCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseCategoryDAOTest {

    @Mock
    private ExpenseCategoryRepository categoryRepository;

    @InjectMocks
    private ExpenseCategoryDAO expenseCategoryDAO;

    @Test
    void testSaveCategory_whenValidCategory_thenReturnSavedCategory() {

        ExpenseCategory category = new ExpenseCategory();
        category.setId(1L);
        category.setName("Food");

        when(categoryRepository.save(category)).thenReturn(category);


        ExpenseCategory savedCategory = expenseCategoryDAO.saveCategory(category);


        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isEqualTo(1L);
        assertThat(savedCategory.getName()).isEqualTo("Food");
    }
}
