package com.ms001.bank.controller;

import com.ms001.bank.dto.ATMDTO;
import com.ms001.bank.dto.BankDTO;
import com.ms001.bank.dto.request.ATMRequestDTO;
import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;
import com.ms001.bank.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bank")
public class BankController {
    private BankService bankService;
    @GetMapping("/{name}")
    public ResponseEntity<BankDTO> getBankById(@PathVariable String name) {
        BankDTO bankDTO = bankService.getBankkByName(name);
        return new ResponseEntity<>(bankDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BankDTO> createBank(@RequestBody BankCreateRequestDTO bankCreateRequestDTO) {
        BankDTO bankDTO = bankService.createBank(bankCreateRequestDTO);
        return new ResponseEntity<>(bankDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<BankDTO> updateBank(@PathVariable String name, @RequestBody BankUpdateRequestDTO bankUpdateRequestDTO) {
        BankDTO bankDTO = bankService.updateBank(name, bankUpdateRequestDTO);
        return new ResponseEntity<>(bankDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteBankByid(@PathVariable String name) {
        bankService.deleteBankByid(name);
        return ResponseEntity.noContent().build();
    }
}
