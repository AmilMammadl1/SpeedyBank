package com.ms001.bank.controller;

import com.ms001.bank.dto.request.ProcessTransactionDTO;
import com.ms001.bank.dto.response.CardResponseDTO;
import com.ms001.bank.dto.request.CardCreateRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;
import com.ms001.bank.service.CardService;
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
public class CardController {
    private CardService cardService;

//    @GetMapping("/card/all")
    public ResponseEntity<List<CardResponseDTO>> getAllCards() {
        List<CardResponseDTO> allCardResponseDtos = cardService.getAllCards();
        return new ResponseEntity<>(allCardResponseDtos, HttpStatus.OK);
    }
    @Operation(summary = "Get Card", description = "Get Card")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/customer/card/{id}")
    public ResponseEntity<CardResponseDTO> getCardById(@PathVariable Long id) {
        CardResponseDTO cardResponseDTO = cardService.getCardById(id);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Create Card", description = "Create Card")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/customer/card/create")
    public ResponseEntity<CardResponseDTO> createCard(@Valid @RequestBody CardCreateRequestDTO cardCreateRequestDTO) {
        CardResponseDTO cardResponseDTO = cardService.createCard(cardCreateRequestDTO);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.CREATED);
    }
    @Operation(summary = "Update Card", description = "Update Card")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/customer/card/update/{id}")
    public ResponseEntity<CardResponseDTO> updateCard(@PathVariable Long id, @Valid @RequestBody CardUpdateRequestDTO cardUpdateRequestDTO) {
        CardResponseDTO cardResponseDTO = cardService.updateCard(cardUpdateRequestDTO, id);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Card Transaction", description = "Card Transaction")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/customer/card/transaction")
    public ResponseEntity<CardResponseDTO> CardTransaction(@Valid @RequestBody ProcessTransactionDTO processTransactionDTO) {
        CardResponseDTO cardResponseDTO = cardService.CardTransaction(processTransactionDTO);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Delete Card", description = "Delete Card")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/card/delete/{id}")
    public ResponseEntity<Void> deleteCardByid(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
