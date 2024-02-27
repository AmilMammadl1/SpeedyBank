package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.CardDTO;
import com.ms001.bank.dto.request.CardRequestDTO;
import com.ms001.bank.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Override
    public List<CardDTO> getAllCards() {
        return null;
    }

    @Override
    public CardDTO getCardById(Long id) {
        return null;
    }

    @Override
    public List<CardDTO> getCardsByUserId(Long id) {
        return null;
    }

    @Override
    public CardDTO updateCard(CardRequestDTO cardRequestDTO, Long id) {
        return null;
    }

    @Override
    public CardDTO createCard(CardRequestDTO cardRequestDTO) {
        return null;
    }

    @Override
    public void deleteCard(Long id) {

    }
}
