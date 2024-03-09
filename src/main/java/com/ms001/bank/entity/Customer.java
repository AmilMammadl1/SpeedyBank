package com.ms001.bank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String password = null;
    private String phoneNumber;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();
    public Customer() {
        // Create a new Account with default values and set bidirectional relationship
        this.account = new Account();
        this.account.setCreatedAt(new Date());
        this.account.setCustomer(this);
        this.account.setBalanceTotal(0.0);
        this.account.setActive(true);
    }
}
