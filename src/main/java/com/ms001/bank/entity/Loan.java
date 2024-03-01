package com.ms001.bank.entity;

import jakarta.persistence.*;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loanNumber;
    private Double amount;
    private Double interestRate;
    private String term;
    private String approvalStatus;
    private String repaymentHistory;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}