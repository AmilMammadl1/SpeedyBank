package com.ms001.bank.entity;

import com.ms001.bank.constant.AccountType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private double balanceTotal;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private boolean isActive;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToOne(mappedBy = "account")
    private User user;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();
}
