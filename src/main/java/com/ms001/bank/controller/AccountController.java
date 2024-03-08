package com.ms001.bank.controller;

import com.ms001.bank.dto.response.AccountResponseDTO;
import com.ms001.bank.dto.request.AccountRequestDTO;
import com.ms001.bank.service.AccountService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> allAccount = accountService.getAllAccount();
        return new ResponseEntity<>(allAccount, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        AccountResponseDTO accountById = accountService.getAccountById(id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountResponseDTO> createAccount(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO accountById = accountService.updateAccount(accountRequestDTO, id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<Void> deleteAccountByUserId(@PathVariable Long id) {
        accountService.deleteAccountByUserId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
