package com.ms001.bank.controller;

import com.ms001.bank.dto.response.BankResponseDTO;
import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.service.BankService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/bank")
public class BankController {
    private BankService bankService;
    @GetMapping("/{name}")
    public ResponseEntity<BankResponseDTO> getBankById(@PathVariable String name) {
        BankResponseDTO bankResponseDTO = bankService.getBankByName(name);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<Bank> all() {
        return bankService.getBankAll();
    }

    @PostMapping("/create")
    public ResponseEntity<BankResponseDTO> createBank(@Valid @RequestBody BankCreateRequestDTO bankCreateRequestDTO) {
        BankResponseDTO bankResponseDTO = bankService.createBank(bankCreateRequestDTO);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<BankResponseDTO> updateBank(@PathVariable String name, @Valid @RequestBody BankUpdateRequestDTO bankUpdateRequestDTO) {
        BankResponseDTO bankResponseDTO = bankService.updateBank(name, bankUpdateRequestDTO);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteBankByid(@PathVariable String name) {
        bankService.deleteBankByid(name);
        return ResponseEntity.noContent().build();
    }
}
