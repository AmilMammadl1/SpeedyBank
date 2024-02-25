package com.ms001.bank.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", unique = true)
    private Account account;
    @ManyToMany(mappedBy = "users")
    private List<Bank> banks = new ArrayList<>();

}
