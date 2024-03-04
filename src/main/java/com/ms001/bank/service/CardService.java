package com.ms001.bank.service;

import com.ms001.bank.dto.CardDTO;
import com.ms001.bank.dto.request.CardRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getAllCards();
    List<CardDTO> getAllCardsByAccountId(Long id);

    CardDTO getCardById(Long id);
    CardDTO updateCard(CardUpdateRequestDTO cardUpdateRequestDTO, Long id);
    CardDTO createCard(CardRequestDTO cardRequestDTO);
    void deleteCard(Long id);

}
