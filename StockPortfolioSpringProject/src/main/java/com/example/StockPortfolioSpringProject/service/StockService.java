package com.example.StockPortfolioSpringProject.service;

import com.example.StockPortfolioSpringProject.dto.Stock;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockService {
    public List<Stock> getAllStocks() {
        return List.of(
                new Stock("INFY", "Infosys", 1500.00),
                new Stock("TCS", "Tata Consultancy Services", 3200.50)
        );
    }
}
