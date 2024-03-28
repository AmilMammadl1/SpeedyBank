package com.ms001.bank.controller;

import com.ms001.bank.dto.response.TransactionResponseDTO;
import com.ms001.bank.dto.request.TransactionCreateRequestDTO;
import com.ms001.bank.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TransactionController {
    private TransactionService transactionService;

    @Operation(summary = "Get All Transaction", description = "Get All Transaction")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/developer/transaction/all")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<TransactionResponseDTO> allTransactionResponseDTOS = transactionService.getAllTransaction();
        return new ResponseEntity<>(allTransactionResponseDTOS, HttpStatus.OK);
    }

    @Operation(summary = "Get Transaction", description = "Get Transaction")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/developer/transaction/id/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable String name) {
        TransactionResponseDTO transactionResponseDTO = transactionService.getTransactionById(name);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Create Transaction", description = "Create Transaction")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/developer/transaction/create")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionCreateRequestDTO transactionCreateRequestDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionService.createTransaction(transactionCreateRequestDTO);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete Transaction", description = "Delete Transaction")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/transaction/delete/{name}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable String name) {
        transactionService.deleteTransaction(name);
        return ResponseEntity.noContent().build();
    }
}
