package com.example.WeekendTaskJul27.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String name;
    private String email;
    private String password;


    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;
}
