package com.example.WeekendTaskJul27;

import com.example.WeekendTaskJul27.service.Ecommerce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WeekendTaskJul27Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WeekendTaskJul27Application.class, args);
        Ecommerce ecommerce = context.getBean(Ecommerce.class);
        ecommerce.startShopping();
    }
}
