package com.ms001.bank.controller;

import com.ms001.bank.dto.response.BankResponseDTO;
import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.service.BankService;
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
public class BankController {
    private BankService bankService;

    @Operation(summary = "Get Bank", description = "Get Bank")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/bank/name/{name}")
    public ResponseEntity<BankResponseDTO> getBankById(@PathVariable String name) {
        BankResponseDTO bankResponseDTO = bankService.getBankByName(name);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Create Bank", description = "Create Bank")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/admin/bank/create")
    public ResponseEntity<BankResponseDTO> createBank(@Valid @RequestBody BankCreateRequestDTO bankCreateRequestDTO) {
        BankResponseDTO bankResponseDTO = bankService.createBank(bankCreateRequestDTO);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Bank", description = "Update Bank")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/admin/bank/update/{name}")
    public ResponseEntity<BankResponseDTO> updateBank(@PathVariable String name, @Valid @RequestBody BankUpdateRequestDTO bankUpdateRequestDTO) {
        BankResponseDTO bankResponseDTO = bankService.updateBank(name, bankUpdateRequestDTO);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete Bank", description = "Delete Bank")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/bank//delete/{name}")
    public ResponseEntity<Void> deleteBankByid(@PathVariable String name) {
        bankService.deleteBankByid(name);
        return ResponseEntity.noContent().build();
    }
}
