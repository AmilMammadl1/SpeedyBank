package com.ms001.bank.service;

import com.ms001.bank.dto.TransactionDTO;
import com.ms001.bank.dto.request.TransactionRequestDTO;
import com.ms001.bank.dto.request.UserCreateRequestDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getAllTransaction();
    TransactionDTO getTransactionById(Long id);
    TransactionDTO createTransaction(TransactionRequestDTO transactionRequestDTO);
    void deleteTransaction(Long id);
}
