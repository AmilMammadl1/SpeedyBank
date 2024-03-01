package com.ms001.bank.service.Impl;

import com.ms001.bank.constant.TransactionType;
import com.ms001.bank.dto.CardDTO;
import com.ms001.bank.dto.request.CardRequestDTO;
import com.ms001.bank.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
//    public void processTransaction(Long cardId, double transactionAmount, TransactionType transactionType) {
//        Card card = cardRepository.findById(cardId)
//                .orElseThrow(() -> new EntityNotFoundException("Card not found with ID: " + cardId));
//
//        Transaction transaction = new Transaction();
//        transaction.setCard(card);
//        transaction.setAmount(transactionAmount);
//        transaction.setType(transactionType);
//
//        // Save the transaction
//        transactionRepository.save(transaction);
//
//        // Update the card balance based on the transaction type
//        card.updateBalance(transaction);
//        cardRepository.save(card);
//    }

    @Override
    public List<CardDTO> getAllCards() {
        return null;
    }

    @Override
    public CardDTO getCardById(Long id) {
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
