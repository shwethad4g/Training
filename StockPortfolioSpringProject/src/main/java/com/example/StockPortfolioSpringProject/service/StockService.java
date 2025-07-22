package com.example.StockPortfolioSpringProject.service;

import com.example.StockPortfolioSpringProject.dto.StockDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    public List<StockDto> getAllStocks() {
        List<StockDto> stock = new ArrayList<>();

        stock.add(new StockDto("INFY", "Infosys", 1500.00));
        stock.add(new StockDto("TCS", "Tata Consultancy Services", 3200.50));
        stock.add(new StockDto("WIPRO", "Wipro Ltd", 430.75));

        return stock;
    }
}
