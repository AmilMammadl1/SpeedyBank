package com.ms001.bank.entity;

import com.ms001.bank.constant.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToMany(mappedBy = "transactions")
    private List<Card> cards = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
}
