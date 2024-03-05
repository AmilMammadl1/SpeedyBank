package com.ms001.bank.controller;

import com.ms001.bank.dto.AccountDTO;
import com.ms001.bank.dto.TransactionDTO;
import com.ms001.bank.dto.request.AccountRequestDTO;
import com.ms001.bank.dto.request.TransactionRequestDTO;
import com.ms001.bank.entity.Transaction;
import com.ms001.bank.service.AccountService;
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
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> allTransactionDTOS = transactionService.getAllTransaction();
        return new ResponseEntity<>(allTransactionDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        TransactionDTO transactionDTO = transactionService.createTransaction(transactionRequestDTO);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{idi")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
