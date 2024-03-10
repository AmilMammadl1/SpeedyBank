package com.ms001.bank.service;

import com.ms001.bank.dto.request.ProcessTransactionDTO;
import com.ms001.bank.dto.response.CardResponseDTO;
import com.ms001.bank.dto.request.CardCreateRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;

import java.util.List;

public interface CardService {
    List<CardResponseDTO> getAllCards();

    List<CardResponseDTO> getAllCardsByAccountId(Long id);

    CardResponseDTO CardTransaction(ProcessTransactionDTO processTransactionDTO);

    CardResponseDTO getCardById(Long id);

    CardResponseDTO updateCard(CardUpdateRequestDTO cardUpdateRequestDTO, Long id);

    CardResponseDTO createCard(CardCreateRequestDTO cardCreateRequestDTO);

    void deleteCard(Long id);

}
