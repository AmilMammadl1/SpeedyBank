package com.ms001.bank.controller;

import com.ms001.bank.dto.request.ATMCreateRequestDTO;
import com.ms001.bank.dto.request.ATMUpdateRequestDTO;
import com.ms001.bank.dto.response.ATMResponseDTO;
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
    public ResponseEntity<List<ATMResponseDTO>> getAllATMs() {
        List<ATMResponseDTO> allATM = atmService.getAllATM();
        return new ResponseEntity<>(allATM, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ATMResponseDTO> getATMById(@PathVariable Long id) {
        ATMResponseDTO ATMResponseDTO = atmService.getATMById(id);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ATMResponseDTO> createATM(@RequestBody ATMCreateRequestDTO atmCreateRequestDTO) {
        ATMResponseDTO ATMResponseDTO = atmService.createATM(atmCreateRequestDTO);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ATMResponseDTO> updateATM(@PathVariable Long id, @RequestBody ATMUpdateRequestDTO atmUpdateRequestDTO) {
        ATMResponseDTO ATMResponseDTO = atmService.updateATM(id, atmUpdateRequestDTO);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteATMByid(@PathVariable Long id) {
        atmService.deleteATM(id);
        return ResponseEntity.noContent().build();
    }
}
