package com.example.StockPortfolioSpringProject.service;

import com.example.StockPortfolioSpringProject.dto.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    public List<Stock> getAllStocks() {
        List<Stock> stock = new ArrayList<>();

        stock.add(new Stock("INFY", "Infosys", 1500.00));
        stock.add(new Stock("TCS", "Tata Consultancy Services", 3200.50));
        stock.add(new Stock("WIPRO", "Wipro Ltd", 430.75));

        return stock;
    }
}
