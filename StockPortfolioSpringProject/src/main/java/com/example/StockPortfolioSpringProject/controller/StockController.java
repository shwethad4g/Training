package com.example.StockPortfolioSpringProject.controller;


import com.example.StockPortfolioSpringProject.dto.Stock;
import com.example.StockPortfolioSpringProject.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> fetchStocks() {
        return stockService.getAllStocks();
    }
}
    