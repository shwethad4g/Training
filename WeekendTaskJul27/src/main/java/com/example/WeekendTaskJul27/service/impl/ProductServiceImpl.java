package com.example.WeekendTaskJul27.service.impl;




import com.example.WeekendTaskJul27.dto.ProductDto;
import com.example.WeekendTaskJul27.mapper.ProductMapper;
import com.example.WeekendTaskJul27.repository.ProductRepository;
import com.example.WeekendTaskJul27.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return mapper.toDtoList(repository.findAll());
    }
}
