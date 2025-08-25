package com.example.Personal_Finance_Tracker_Final_Project.repository;




import com.example.Personal_Finance_Tracker_Final_Project.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i WHERE i.user.id = :userId")
    Double getTotalIncomesByUserId(Long userId);

    Optional<Income> findByIdAndUserId(Long id, Long userId);
}