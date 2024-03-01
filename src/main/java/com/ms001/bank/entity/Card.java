package com.ms001.bank.entity;

import com.ms001.bank.constant.CardType;
import com.ms001.bank.constant.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cardNumber;    private Date expirationDate;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private boolean isActive;
    private double balance;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    private String generateUniqueCardNumber() {
        // Fixed prefix for the card number
        String prefix = "88186818";

        // Generate a unique portion using UUID
        String uniquePortion = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        // Concatenate the prefix and unique portion to create the full card number
        return prefix + uniquePortion;
    }
    public Card(CardType cardType, Account account) {
        this.cardNumber = generateUniqueCardNumber();
        this.expirationDate = calculateExpirationDate();
        this.cardType = cardType;
        this.isActive = true;
        this.balance = 0.0;
        this.createdAt = new Date();
        this.account = account;
    }
    public void updateBalance(Transaction transaction) {
        double currentBalance = getBalance();
        double transactionAmount = transaction.getAmount();

        // Adjust for withdrawals
        if (TransactionType.WITHDRAWAL.equals(transaction.getTransactionType())) {
            transactionAmount *= -1;
        }

        double newBalance = currentBalance + transactionAmount;
        setBalance(newBalance);
    }
    private Date calculateExpirationDate() {
        // Implement your logic to calculate the expiration date, for example adding 3 years to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3);
        return calendar.getTime();
    }

}
