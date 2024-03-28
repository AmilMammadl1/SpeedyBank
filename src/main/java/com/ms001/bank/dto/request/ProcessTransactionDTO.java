package com.ms001.bank.dto.request;

import com.ms001.bank.constant.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessTransactionDTO {
    @NotNull(message = "Card field cannot be null")
    private Long id;
    @NotBlank(message = "Transaction type cannot be blank")
    private String transactionName;
    @NotNull(message = "Amount field cannot be null")
    private double amount;
    @NotNull(message = "Card Number type field cannot be null")
    private String cardNumber;
}
