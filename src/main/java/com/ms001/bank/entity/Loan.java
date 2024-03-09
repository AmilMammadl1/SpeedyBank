package com.ms001.bank.entity;

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
    private Double amount;
    //    @Column(name = "term", columnDefinition = "bigint")
    @Column(name = "term", columnDefinition = "BIGINT USING term::bigint")
    private Long term;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}