package com.ms001.bank.entity;

import com.ms001.bank.constant.LoanTerm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    //    @Column(name = "term", columnDefinition = "bigint")
    @Enumerated(EnumType.STRING) // Specify that the enum should be stored as a string in the database
    private LoanTerm termOfCredit;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}