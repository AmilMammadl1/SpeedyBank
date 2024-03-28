package com.ms001.bank.controller;

import com.ms001.bank.dto.response.AccountResponseDTO;
import com.ms001.bank.dto.request.AccountUpdateRequestDTO;
import com.ms001.bank.service.AccountService;
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
public class AccountController {
    private AccountService accountService;

    @Operation(summary = "Get All Account", description = "Get All Account")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/account/all")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> allAccount = accountService.getAllAccount();
        return new ResponseEntity<>(allAccount, HttpStatus.OK);
    }

    @Operation(summary = "Get Account", description = "Get Account")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/customer/account/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        AccountResponseDTO accountById = accountService.getAccountById(id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @Operation(summary = "Update Account", description = "Update Account")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/customer/account/update/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountUpdateRequestDTO accountUpdateRequestDTO) {
        AccountResponseDTO accountById = accountService.updateAccount(accountUpdateRequestDTO, id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @Operation(summary = "Delete Account", description = "Delete Account")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/delete/account/{id}")
    public ResponseEntity<Void> deleteAccountByUserId(@PathVariable Long id) {
        accountService.deleteAccountByUserId(id);
        return ResponseEntity.noContent().build();
    }


}
