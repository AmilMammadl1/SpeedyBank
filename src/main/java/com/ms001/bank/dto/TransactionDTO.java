package com.ms001.bank.dto;

import com.ms001.bank.constant.TransactionType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionDTO {
    private Long id;
    private BigDecimal amount;
    private Date transactionDate;
    private TransactionType transactionType;
    private List<Long> cardIds;  // Assuming you want to include card IDs in the DTO
    private Long currencyId;
}
