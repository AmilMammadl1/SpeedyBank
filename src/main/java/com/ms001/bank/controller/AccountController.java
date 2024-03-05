package com.ms001.bank.controller;

import com.ms001.bank.dto.AccountDTO;
import com.ms001.bank.dto.request.AccountRequestDTO;
import com.ms001.bank.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> allAccount = accountService.getAllAccount();
        return new ResponseEntity<>(allAccount, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO accountById = accountService.getAccountById(id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @PutMapping("/update/{idi")
    public ResponseEntity<AccountDTO> createAccount(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        AccountDTO accountById = accountService.updateAccount(accountRequestDTO, id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{idi")
    public ResponseEntity<Void> deleteAccountByUserId(@PathVariable Long id) {
        accountService.deleteAccountByUserId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idi")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
