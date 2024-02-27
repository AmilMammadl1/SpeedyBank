package com.ms001.bank.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Branch> branches = new ArrayList<>();
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<ATM> atms = new ArrayList<>();
    @ManyToMany(mappedBy = "banks")
    private List<Employee> employees = new ArrayList<>();
    @ManyToMany(mappedBy = "banks")
    private List<User> users = new ArrayList<>();
}