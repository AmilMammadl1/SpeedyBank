package com.ms001.bank.service;

import com.ms001.bank.dto.response.TransactionResponseDTO;
import com.ms001.bank.dto.request.TransactionCreateRequestDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDTO> getAllTransaction();
    TransactionResponseDTO getTransactionById(String name);
    TransactionResponseDTO createTransaction(TransactionCreateRequestDTO transactionCreateRequestDTO);
    void deleteTransaction(String name);
}
