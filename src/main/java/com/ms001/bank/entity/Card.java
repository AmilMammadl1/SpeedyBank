package com.ms001.bank.entity;

import com.ms001.bank.constant.CardType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private Date expirationDate;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private boolean isActive;
    private double balanceCard;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
