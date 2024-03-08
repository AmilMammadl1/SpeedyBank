package com.ms001.bank.controller;

import com.ms001.bank.dto.response.TransactionResponseDTO;
import com.ms001.bank.dto.request.TransactionRequestDTO;
import com.ms001.bank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<TransactionResponseDTO> allTransactionResponseDTOS = transactionService.getAllTransaction();
        return new ResponseEntity<>(allTransactionResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
        TransactionResponseDTO transactionResponseDTO = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionService.createTransaction(transactionRequestDTO);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
