package com.ms001.bank.dto.request;

import com.ms001.bank.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessTransactionDTO {
    private Long id;
    private String transactionName;
    private double amount;
}
