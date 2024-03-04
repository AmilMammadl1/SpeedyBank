package com.ms001.bank.service.Impl;

import com.ms001.bank.constant.TransactionType;
import com.ms001.bank.dto.CardDTO;
import com.ms001.bank.dto.request.CardRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;
import com.ms001.bank.entity.Account;
import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Transaction;
import com.ms001.bank.repository.*;
import com.ms001.bank.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private ModelMapper modelMapper;
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private CardRepository cardRepository;

    public void processTransaction(Long cardId, double transactionAmount, TransactionType transactionType) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with ID: " + cardId));

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);

        // Save the transaction
        transactionRepository.save(transaction);

        // Update the card balance based on the transaction type
        card.updateBalance(transaction);
        cardRepository.save(card);
    }

    @Override
    public List<CardDTO> getAllCards() {
        List<Card> all = cardRepository.findAll();
        List<CardDTO> collect = all.stream().map(card -> modelMapper.map(card, CardDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<CardDTO> getAllCardsByAccountId(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        List<Card> cards = account.getCards();
        List<CardDTO> collect = cards.stream().map(card -> modelMapper.map(card, CardDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CardDTO getCardById(Long id) {
        Card card = cardRepository.findById(id).orElseThrow();
        CardDTO map = modelMapper.map(card, CardDTO.class);
        return map;
    }

    @Override
    public CardDTO updateCard(CardUpdateRequestDTO cardUpdateRequestDTO, Long id) {
        Card card = cardRepository.findById(id).orElseThrow();
        card.setCardType(cardUpdateRequestDTO.getCardType());
        card.setActive(cardUpdateRequestDTO.getIsActive());
        Card savedCard = cardRepository.save(card);
        CardDTO cardDTO = modelMapper.map(savedCard, CardDTO.class);
        return cardDTO;
    }

    @Override
    public CardDTO createCard(CardRequestDTO cardRequestDTO) {
        Long accountId = cardRequestDTO.getAccountId();
        Account account = accountRepository.findById(accountId).orElseThrow();
        Card card = new Card(cardRequestDTO.getCardType(),account);
        Card save = cardRepository.save(card);
        CardDTO map = modelMapper.map(save, CardDTO.class);
        return map;
    }

    @Override
    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow();
        cardRepository.deleteById(id);
    }
}
