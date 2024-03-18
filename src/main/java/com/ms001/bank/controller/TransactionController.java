package com.ms001.bank.controller;

import com.ms001.bank.dto.response.TransactionResponseDTO;
import com.ms001.bank.dto.request.TransactionCreateRequestDTO;
import com.ms001.bank.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<TransactionResponseDTO> allTransactionResponseDTOS = transactionService.getAllTransaction();
        return new ResponseEntity<>(allTransactionResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable String name) {
        TransactionResponseDTO transactionResponseDTO = transactionService.getTransactionById(name);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionCreateRequestDTO transactionCreateRequestDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionService.createTransaction(transactionCreateRequestDTO);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable String name) {
        transactionService.deleteTransaction(name);
        return ResponseEntity.noContent().build();
    }
}
