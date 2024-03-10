package com.ms001.bank.dto.request;

import com.ms001.bank.constant.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequestDTO {
    @NotNull(message = "Transaction type field cannot be null")
    private TransactionType transactionType;
}
