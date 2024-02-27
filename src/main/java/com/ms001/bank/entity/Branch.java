package com.ms001.bank.entity;

import jakarta.persistence.*;

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
    private String location;
    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
}
