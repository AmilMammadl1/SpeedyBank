package com.ms001.bank.controller;

import com.ms001.bank.dto.BranchDTO;
import com.ms001.bank.dto.CardDTO;
import com.ms001.bank.dto.request.BranchRequestDTO;
import com.ms001.bank.dto.request.CardCreateRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;
import com.ms001.bank.entity.Card;
import com.ms001.bank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/card")
public class CardController {
    private CardService cardService;
    @GetMapping("/all")
    public ResponseEntity<List<CardDTO>> getAllCards() {
        List<CardDTO> allCardDtos = cardService.getAllCards();
        return new ResponseEntity<>(allCardDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id) {
        CardDTO cardDTO = cardService.getCardById(id);
        return new ResponseEntity<>(cardDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CardDTO> createCard(@RequestBody CardCreateRequestDTO cardCreateRequestDTO) {
        CardDTO cardDTO = cardService.createCard( cardCreateRequestDTO);
        return new ResponseEntity<>(cardDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Long id, @RequestBody CardUpdateRequestDTO cardUpdateRequestDTO) {
        CardDTO cardDTO = cardService.updateCard(cardUpdateRequestDTO,id);
        return new ResponseEntity<>(cardDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCardByid(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
