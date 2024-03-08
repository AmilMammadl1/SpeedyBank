package com.ms001.bank.entity;

import com.ms001.bank.constant.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balanceTotal;
    private boolean isActive;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();
    public Account() {

        // Create a new Card with default values and add it to the list
        Card defaultCard = new Card();
        defaultCard.setCardNumber(generateUniqueCardNumber());

        // Set expiration date to 10 years from the current date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 10);
        defaultCard.setExpirationDate(calendar.getTime());

        // Set other default values
        defaultCard.setCardType(CardType.DEBIT);
        defaultCard.setActive(true);
        defaultCard.setBalance(0.0);
        defaultCard.setCreatedAt(new Date());

        // Set bidirectional relationship
        defaultCard.setAccount(this);

        this.cards.add(defaultCard);
    }
    private String generateUniqueCardNumber() {
        // Fixed prefix for the card number
        String prefix = "88186818";

        // Generate a unique portion using UUID
        String uniquePortion = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        // Concatenate the prefix and unique portion to create the full card number
        return prefix + uniquePortion;
    }
}
