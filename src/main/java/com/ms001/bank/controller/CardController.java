package com.ms001.bank.controller;

import com.ms001.bank.dto.request.ProcessTransactionDTO;
import com.ms001.bank.dto.response.CardResponseDTO;
import com.ms001.bank.dto.request.CardCreateRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;
import com.ms001.bank.service.CardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@AllArgsConstructor
//@RequestMapping("/api")
public class CardController {
    private CardService cardService;

    @GetMapping("/card//all")
    public ResponseEntity<List<CardResponseDTO>> getAllCards() {
        List<CardResponseDTO> allCardResponseDtos = cardService.getAllCards();
        return new ResponseEntity<>(allCardResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<CardResponseDTO> getCardById(@PathVariable Long id) {
        CardResponseDTO cardResponseDTO = cardService.getCardById(id);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CardResponseDTO> createCard(@Valid @RequestBody CardCreateRequestDTO cardCreateRequestDTO) {
        CardResponseDTO cardResponseDTO = cardService.createCard(cardCreateRequestDTO);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CardResponseDTO> updateCard(@PathVariable Long id, @Valid @RequestBody CardUpdateRequestDTO cardUpdateRequestDTO) {
        CardResponseDTO cardResponseDTO = cardService.updateCard(cardUpdateRequestDTO, id);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }
    @PutMapping("/card/transaction")
    public ResponseEntity<CardResponseDTO> CardTransaction(@Valid @RequestBody ProcessTransactionDTO processTransactionDTO) {
        CardResponseDTO cardResponseDTO = cardService.CardTransaction(processTransactionDTO);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);

    }

    @DeleteMapping("/admin/card/delete/{id}")
    public ResponseEntity<Void> deleteCardByid(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
