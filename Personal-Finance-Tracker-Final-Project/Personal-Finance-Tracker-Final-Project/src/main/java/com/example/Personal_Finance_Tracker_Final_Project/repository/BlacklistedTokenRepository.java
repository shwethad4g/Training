package com.example.Personal_Finance_Tracker_Final_Project.repository;


import com.example.Personal_Finance_Tracker_Final_Project.model.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    boolean existsByToken(String token);
}