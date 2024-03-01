package com.ms001.bank.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String password;
    private String phoneNumber;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();
    public User(String firstName, String lastName, Bank bank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bank = bank;

        // Create a new Account with default values and set bidirectional relationship
        this.account = new Account();
        this.account.setCreatedAt(new Date());
        this.account.setUser(this);
        this.account.setBalanceTotal(0.0);
        this.account.setActive(true);
    }
}
