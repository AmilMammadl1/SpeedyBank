package com.ms001.bank.service;

import com.ms001.bank.dto.response.TransactionResponseDTO;
import com.ms001.bank.dto.request.TransactionRequestDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDTO> getAllTransaction();
    TransactionResponseDTO getTransactionById(Long id);
    TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO);
    void deleteTransaction(Long id);
}
