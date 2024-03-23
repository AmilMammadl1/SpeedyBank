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
@Setter
@Getter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Column(unique = true)
    private String cardNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private boolean isActive;
    private double balance;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


    public Card() {
        this.cardNumber = generateUniqueCardNumber();
        this.expirationDate = calculateExpirationDate();
        this.isActive = true;
        this.balance = 0.0;
        this.createdAt = new Date();

    }
    private String generateUniqueCardNumber() {
        // Fixed prefix for the card number
        String prefix = "88186818";

        // Generate a unique portion using UUID
        String uniquePortion = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);

        // Concatenate the prefix and unique portion to create the full card number
        return prefix + uniquePortion;
    }

    public double updateBalance(Transaction transaction, double transactionAmount) {
        double currentBalance = getBalance();
        double resultBalance = currentBalance;
//        double transactionAmount = transaction.getAmount();

        // Adjust for withdrawals
        if (TransactionType.WITHDRAWAL.equals(transaction.getTransactionType())) {
            transactionAmount *= -1;
            resultBalance = transactionAmount + currentBalance;
        } else if (TransactionType.DEPOSIT.equals(transaction.getTransactionType())) {
            transactionAmount *= 1;
            resultBalance = transactionAmount + currentBalance;
        }
        return resultBalance;
    }

    private Date calculateExpirationDate() {
        // Implement your logic to calculate the expiration date, for example adding 3 years to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3);
        return calendar.getTime();
    }

    public boolean checkCardIsActive() {
        if (isActive == true && expirationDate.before(new Date())) {
            return true;
        }
        return false;
    }
    public void addCreditBalanceToCardBalance(double amount){
        balance = balance+amount;
    }
}
