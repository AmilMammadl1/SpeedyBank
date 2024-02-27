package com.ms001.bank.dto;

import com.ms001.bank.constant.AccountType;

import java.util.Date;
import java.util.List;

public class AccountDTO {
    private Long id;
    private String accountNumber;
    private double balanceTotal;
    private AccountType accountType;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private Long userId;  // Assuming you want to include user ID in the DTO
    private List<Long> cardIds;
}
