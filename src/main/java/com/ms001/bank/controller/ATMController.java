package com.ms001.bank.controller;

import com.ms001.bank.dto.ATMDTO;
import com.ms001.bank.dto.AccountDTO;
import com.ms001.bank.dto.request.ATMRequestDTO;
import com.ms001.bank.repository.ATMRepository;
import com.ms001.bank.service.ATMService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ATM")
public class ATMController {
    private ATMService atmService;

    @GetMapping("/all")
    public ResponseEntity<List<ATMDTO>> getAllATMs() {
        List<ATMDTO> allATM = atmService.getAllATM();
        return new ResponseEntity<>(allATM, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ATMDTO> getATMById(@PathVariable Long id) {
        ATMDTO atmdto = atmService.getATMById(id);
        return new ResponseEntity<>(atmdto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ATMDTO> createATM(@RequestBody ATMRequestDTO atmRequestDTO) {
        ATMDTO atmdto = atmService.createATM(atmRequestDTO);
        return new ResponseEntity<>(atmdto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ATMDTO> updateATM(@PathVariable Long id, @RequestBody ATMRequestDTO atmRequestDTO) {
        ATMDTO atmdto = atmService.updateATM(id, atmRequestDTO);
        return new ResponseEntity<>(atmdto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteATMByid(@PathVariable Long id) {
        atmService.deleteATM(id);
        return ResponseEntity.noContent().build();
    }
}
