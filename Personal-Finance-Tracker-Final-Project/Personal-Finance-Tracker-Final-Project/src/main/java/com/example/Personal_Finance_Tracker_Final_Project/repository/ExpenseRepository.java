package com.example.Personal_Finance_Tracker_Final_Project.repository;

import com.example.Personal_Finance_Tracker_Final_Project.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    List<Expense> findByUser_IdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);


    @Query("select coalesce(sum(e.amount), 0) from Expense e where e.user.id = :userId")
    BigDecimal getTotalExpensesByUserId(@Param("userId") Long userId);


    @Query("""
                select coalesce(sum(e.amount), 0)
                from Expense e
                where e.user.id = :userId
                  and e.date between :startDate and :endDate
            """)
    BigDecimal getTotalExpensesByUserIdAndDateBetween(@Param("userId") Long userId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);


    Optional<Expense> findByIdAndUser_Id(Long id, Long userId);


    @Query("""
                select coalesce(e.category, 'Uncategorized'),
                       coalesce(sum(e.amount), 0)
                from Expense e
                where e.user.id = :userId
                  and e.date between :startDate and :endDate
                group by e.category
                order by coalesce(e.category, 'Uncategorized')
            """)
    List<Object[]> findExpensesByCategoryAndUserIdAndDateBetween(@Param("userId") Long userId,
                                                                 @Param("startDate") LocalDate startDate,
                                                                 @Param("endDate") LocalDate endDate);


    @Query("""
                select e.date, coalesce(sum(e.amount), 0)
                from Expense e
                where e.user.id = :userId
                  and e.date between :startDate and :endDate
                group by e.date
                order by e.date
            """)
    List<Object[]> findExpensesByDateAndUserIdAndDateBetween(@Param("userId") Long userId,
                                                             @Param("startDate") LocalDate startDate,
                                                             @Param("endDate") LocalDate endDate);

    @Query("""
                select coalesce(e.category, 'Uncategorized'),
                       coalesce(sum(e.amount), 0)
                from Expense e
                where e.user.id = :userId
                  and (:startDate is null or e.date >= :startDate)
                  and (:endDate   is null or e.date <= :endDate)
                group by e.category
                order by coalesce(e.category, 'Uncategorized')
            """)
    List<Object[]> findExpensesByCategoryAndUserId(@Param("userId") Long userId,
                                                   @Param("startDate") LocalDate startDate,
                                                   @Param("endDate") LocalDate endDate);


    @Query("""
              select coalesce(e.category, 'Uncategorized'),
                     coalesce(sum(e.amount), 0)
              from Expense e
              where e.user.id = :userId
                and e.date >= coalesce(:startDate, e.date)
                and e.date <= coalesce(:endDate,   e.date)
              group by e.category
              order by coalesce(e.category, 'Uncategorized')
            """)
    List<Object[]> sumByCategoryFlexible(@Param("userId") Long userId,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);


}



