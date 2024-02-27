package com.ms001.bank.dto.request;

import com.ms001.bank.constant.TransactionType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionRequestDTO {
    private BigDecimal amount;
    private TransactionType transactionType;
    private List<Long> cardIds;  // Assuming you want to include card IDs in the request DTO
    private Long currencyId;
}
