package com.ms001.bank.dto.request;

import com.ms001.bank.constant.AccountType;

import java.util.List;

public class AccountRequestDTO {
    private String accountNumber;
    private double balanceTotal;
    private AccountType accountType;
    private boolean isActive;
    private Long userId;
    private List<Long> cardIds;
}
