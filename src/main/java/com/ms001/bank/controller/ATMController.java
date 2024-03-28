package com.ms001.bank.controller;

import com.ms001.bank.dto.request.ATMCreateRequestDTO;
import com.ms001.bank.dto.request.ATMUpdateRequestDTO;
import com.ms001.bank.dto.response.ATMResponseDTO;
import com.ms001.bank.service.ATMService;
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
public class ATMController {
    private ATMService atmService;

    @GetMapping("/atm/all")
    public ResponseEntity<List<ATMResponseDTO>> getAllATMs() {
        List<ATMResponseDTO> allATM = atmService.getAllATM();
        return new ResponseEntity<>(allATM, HttpStatus.OK);
    }

    @GetMapping("/atm/id/{id}")
    public ResponseEntity<ATMResponseDTO> getATMById(@PathVariable Long id) {
        ATMResponseDTO ATMResponseDTO = atmService.getATMById(id);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Create Atm", description = "Create Atm")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/developer/atm/create")
    public ResponseEntity<ATMResponseDTO> createATM(@Valid @RequestBody ATMCreateRequestDTO atmCreateRequestDTO) {
        ATMResponseDTO ATMResponseDTO = atmService.createATM(atmCreateRequestDTO);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.CREATED);
    }
    @Operation(summary = "Update Atm", description = "Update Atm")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/developer/atm/update/{id}")
    public ResponseEntity<ATMResponseDTO> updateATM(@PathVariable Long id, @Valid @RequestBody ATMUpdateRequestDTO atmUpdateRequestDTO) {
        ATMResponseDTO ATMResponseDTO = atmService.updateATM(id, atmUpdateRequestDTO);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Delete Atm", description = "Delete Atm")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/atm/delete/{id}")
    public ResponseEntity<Void> deleteATMByid(@PathVariable Long id) {
        atmService.deleteATM(id);
        return ResponseEntity.noContent().build();
    }
}
