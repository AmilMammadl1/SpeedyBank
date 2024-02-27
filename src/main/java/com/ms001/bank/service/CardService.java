package com.ms001.bank.service;

import com.ms001.bank.dto.CardDTO;
import com.ms001.bank.dto.request.CardRequestDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getAllCards();
    CardDTO getCardById(Long id);
    List<CardDTO> getCardsByUserId(Long id);
    CardDTO updateCard(CardRequestDTO cardRequestDTO,Long id);
    CardDTO createCard(CardRequestDTO cardRequestDTO);
    void deleteCard(Long id);

}
