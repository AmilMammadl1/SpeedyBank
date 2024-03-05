package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.TransactionDTO;
import com.ms001.bank.dto.request.TransactionRequestDTO;
import com.ms001.bank.entity.Transaction;
import com.ms001.bank.repository.TransactionRepository;
import com.ms001.bank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private ModelMapper modelMapper;
    @Override
    public List<TransactionDTO> getAllTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDTO> transactionDTOS = transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class)).collect(Collectors.toList());
        return transactionDTOS;
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
        return transactionDTO;
    }

    @Override
    public TransactionDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionRequestDTO.getTransactionType());
        Transaction savedTransaction = transactionRepository.save(transaction);
        TransactionDTO transactionDTO = modelMapper.map(savedTransaction, TransactionDTO.class);
        return transactionDTO;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.findById(id).orElseThrow();
        transactionRepository.deleteById(id);
    }
}
