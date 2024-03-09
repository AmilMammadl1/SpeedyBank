package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.TransactionResponseDTO;
import com.ms001.bank.dto.request.TransactionCreateRequestDTO;
import com.ms001.bank.entity.Transaction;
import com.ms001.bank.exception.TransactionNotFoundException;
import com.ms001.bank.mapper.TransactionMapper;
import com.ms001.bank.repository.TransactionRepository;
import com.ms001.bank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    @Override
    public List<TransactionResponseDTO> getAllTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponseDTO> transactionResponseDTOS = transactions.stream()
                .map(transaction -> transactionMapper.mapTransactionEntityToTransactionResponseDTO(transaction))
                .collect(Collectors.toList());
        return transactionResponseDTOS;
    }

    @Override
    public TransactionResponseDTO getTransactionById(String name) {
        Transaction transaction = transactionRepository.findById(name).get();
        if(transaction == null){
            throw new TransactionNotFoundException("Transaction not found with name: "+ transaction.getTransactionType());
        }
        TransactionResponseDTO transactionResponseDTO = transactionMapper.mapTransactionEntityToTransactionResponseDTO(transaction);
        return transactionResponseDTO;
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionCreateRequestDTO transactionCreateRequestDTO) {
        Transaction transaction = new Transaction(transactionCreateRequestDTO.getTransactionType());
//        Transaction transaction = transactionMapper.mapTransactionCreateRequestDTOToTransactionEntity(transactionCreateRequestDTO);
        Transaction createdTransaction = transactionRepository.save(transaction);
        TransactionResponseDTO transactionResponseDTO = transactionMapper.mapTransactionEntityToTransactionResponseDTO(createdTransaction);
        return transactionResponseDTO;
    }

    @Override
    public void deleteTransaction(String name) {
        Transaction transaction = transactionRepository.findById(name).get();
        if(transaction == null){
            throw new TransactionNotFoundException("Transaction not found with name: "+ transaction.getName());
        }
        transactionRepository.deleteById(name);
    }
}
