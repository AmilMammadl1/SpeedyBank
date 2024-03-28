package com.ms001.bank.service.Impl;

import com.ms001.bank.constant.CardType;
import com.ms001.bank.constant.TransactionType;
import com.ms001.bank.dto.request.ProcessTransactionDTO;
import com.ms001.bank.dto.response.CardResponseDTO;
import com.ms001.bank.dto.request.CardCreateRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;
import com.ms001.bank.entity.Account;
import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Transaction;
import com.ms001.bank.exception.AccountNotFoundException;
import com.ms001.bank.exception.CardNotFoundException;
import com.ms001.bank.exception.TransactionNotFoundException;
import com.ms001.bank.mapper.CardMapper;
import com.ms001.bank.repository.*;
import com.ms001.bank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private CardMapper cardMapper;
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private CardRepository cardRepository;

    @Override
    public CardResponseDTO CardTransaction(ProcessTransactionDTO processTransactionDTO) {
        Long cardId = processTransactionDTO.getId();
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found with ID: " + cardId));
        Card cardToNextCustomer = cardRepository.findByCardNumber(processTransactionDTO.getCardNumber())
                .orElseThrow(() -> new CardNotFoundException("Card not found with card number: " + processTransactionDTO.getCardNumber()));

        if (card.checkCardIsActive() == true && card.getAccount().checkAccountnIsActive() == true) {
            if (card.getCardType() == CardType.CREDIT || card.getCardType() == CardType.DEBIT) {
                Transaction transaction = transactionRepository.findById(processTransactionDTO.getTransactionName())
                        .orElseThrow(()->new RuntimeException("Transaction is not valid"));

                if (card.getBalance() > 0 && card.getBalance() >= processTransactionDTO.getAmount()) {
                    double updateBalance = card.updateBalance(transaction, processTransactionDTO.getAmount());
                    card.setBalance(updateBalance);
                    if(transaction.getName().equals(TransactionType.WITHDRAWAL.name())){
                        cardToNextCustomer.addBalanceTransaction(processTransactionDTO.getAmount());
                    }
                    cardRepository.save(cardToNextCustomer);

                    Card updatedCardWithTransaction = cardRepository.save(card);
                    CardResponseDTO cardResponseDTO = cardMapper.mapCardEntityToCardResponseDTO(updatedCardWithTransaction);
                    return cardResponseDTO;
                } else {
                    throw new RuntimeException("Card is not active");
                }
            }
        }
        return null;
    }

    @Override
    public List<CardResponseDTO> getAllCards() {
        List<Card> all = cardRepository.findAll();
        List<CardResponseDTO> cardResponseDTOS = all.stream()
                .map(card -> cardMapper.mapCardEntityToCardResponseDTO(card)).collect(Collectors.toList());
        return cardResponseDTOS;
    }

    @Override
    public List<CardResponseDTO> getAllCardsByAccountId(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(("Account not Found with specified id: " + id)));
        List<Card> cards = account.getCards();
        List<CardResponseDTO> cardResponseDTOS = cards.stream()
                .map(card -> cardMapper.mapCardEntityToCardResponseDTO(card)).collect(Collectors.toList());
        return cardResponseDTOS;
    }

    @Override
    public CardResponseDTO getCardById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card not Found with specified id: " + id));
        CardResponseDTO cardResponseDTO = cardMapper.mapCardEntityToCardResponseDTO(card);
        return cardResponseDTO;
    }

    @Override
    public CardResponseDTO updateCard(CardUpdateRequestDTO cardUpdateRequestDTO, Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card not Found with specified id: " + id));
        card.setCardType(cardUpdateRequestDTO.getCardType());
        card.setActive(cardUpdateRequestDTO.getIsActive());
        Card updatedCard = cardRepository.save(card);
        CardResponseDTO cardResponseDTO = cardMapper.mapCardEntityToCardResponseDTO(updatedCard);
        return cardResponseDTO;
    }

    @Override
    public CardResponseDTO createCard(CardCreateRequestDTO cardCreateRequestDTO) {
        Long accountId = cardCreateRequestDTO.getAccountId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not Found with specified id: " + accountId));
        Card card = cardMapper.mapCardCreateRequestDTOToCardEntity(cardCreateRequestDTO);
        card.setAccount(account);
        Card createdCard = cardRepository.save(card);
        CardResponseDTO cardResponseDTO = cardMapper.mapCardEntityToCardResponseDTO(createdCard);
        return cardResponseDTO;
    }

    @Override
    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card not Found with specified id: " + id));
        cardRepository.deleteById(id);
    }
}
