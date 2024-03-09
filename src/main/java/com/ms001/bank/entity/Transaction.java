package com.ms001.bank.entity;

import com.ms001.bank.constant.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @Column(name = "name", unique = true, nullable = false, columnDefinition = "varchar(255) default 'defaultValue'")
    private String name;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    public Transaction(TransactionType transactionType) {
        this.name = transactionType.name();
    }
}
