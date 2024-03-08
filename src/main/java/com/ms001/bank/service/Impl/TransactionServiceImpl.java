package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.TransactionResponseDTO;
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
    public List<TransactionResponseDTO> getAllTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponseDTO> transactionResponseDTOS = transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionResponseDTO.class)).collect(Collectors.toList());
        return transactionResponseDTOS;
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        TransactionResponseDTO transactionResponseDTO = modelMapper.map(transaction, TransactionResponseDTO.class);
        return transactionResponseDTO;
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionRequestDTO.getTransactionType());
        Transaction savedTransaction = transactionRepository.save(transaction);
        TransactionResponseDTO transactionResponseDTO = modelMapper.map(savedTransaction, TransactionResponseDTO.class);
        return transactionResponseDTO;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.findById(id).orElseThrow();
        transactionRepository.deleteById(id);
    }
}
