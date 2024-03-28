package com.ms001.bank.service.Impl;

import com.ms001.bank.constant.CardType;
import com.ms001.bank.constant.LoanTerm;
import com.ms001.bank.dto.request.LoanPayRequestDTO;
import com.ms001.bank.dto.response.CardResponseDTO;
import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Loan;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.exception.CardNotFoundException;
import com.ms001.bank.exception.CustomerNotFoundException;
import com.ms001.bank.exception.LoanNotFoundException;
import com.ms001.bank.mapper.CardMapper;
import com.ms001.bank.mapper.LoanMapper;
import com.ms001.bank.repository.CardRepository;
import com.ms001.bank.repository.LoanRepository;
import com.ms001.bank.repository.CustomerRepository;
import com.ms001.bank.service.LoanService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    private LoanMapper loanMapper;
    private CardMapper cardMapper;
    private LoanRepository loanRepository;
    private CustomerRepository customerRepository;
    private CardRepository cardRepository;
    @Override
    public List<LoanResponseDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanResponseDTO> loanResponseDTOS = loans.stream()
                .map(loan -> loanMapper.mapLoanEntityToLoanResponseDTO(loan))
                .collect(Collectors.toList());
        return loanResponseDTOS;
    }

    @Override
    public List<LoanResponseDTO> getAllLoansOfUser(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));
        List<Loan> loans = customer.getLoans();
        List<LoanResponseDTO> loanResponseDTOS = loans.stream()
                .map(loan -> loanMapper.mapLoanEntityToLoanResponseDTO(loan))
                .collect(Collectors.toList());
        return loanResponseDTOS;
    }

    @Override
    public LoanResponseDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
        LoanResponseDTO loanResponseDTO = loanMapper.mapLoanEntityToLoanResponseDTO(loan);
        return loanResponseDTO;
    }

    @Override
    public LoanResponseDTO updateLoan(LoanUpdateRequestDTO loanUpdateRequestDTO, Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

        LoanTerm term;
        if (loanUpdateRequestDTO.getTerm().equals(6L)) {
            term = LoanTerm.SHORT_TERM;
        } else if (loanUpdateRequestDTO.getTerm().equals(12L)) {
            term = LoanTerm.MEDIUM_TERM;
        } else if (loanUpdateRequestDTO.getTerm().equals(36L)) {
            term = LoanTerm.LONG_TERM;
        } else {
            // Handle the case where the term value is invalid
            throw new IllegalArgumentException("Invalid term value: " + loanUpdateRequestDTO.getTerm());
        }
        loan.setTermOfCredit(term);
        loan.setAmount(loanUpdateRequestDTO.getAmount());
        Loan updatedLoan = loanRepository.save(loan);
        LoanResponseDTO loanResponseDTO = loanMapper.mapLoanEntityToLoanResponseDTO(updatedLoan);
        return loanResponseDTO;
    }

    @Override
    public LoanResponseDTO createLoan(LoanCreateRequestDTO loanCreateRequestDTO) {
        Loan loan = loanMapper.mapLoanCreateRequestDTOToLoanEntity(loanCreateRequestDTO);
        Long customerId = loanCreateRequestDTO.getCustomerId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));
        if (customer.getLoans().size() <= 3) {
            List<Card> cards = customer.getAccount().getCards();
            Card card = cardRepository.findById(loanCreateRequestDTO.getCardId())
                    .orElseThrow(() -> new CardNotFoundException("Card not found with id: " + loanCreateRequestDTO.getCardId()));
            if (!card.getAccount().getCustomer().equals(customer)) {
                throw new IllegalStateException("Card does not belong to the customer associated with the loan create request.");
            }
//        customer.getAccount().addCreditBalanceToTotalBalance(loanCreateRequestDTO.getAmount());

            if (card.checkCardIsActive() && card.getCardType() == CardType.CREDIT) {
                card.addCreditBalanceToCardBalance(loanCreateRequestDTO.getAmount());
                cardRepository.save(card);
            }
            loan.setCustomer(customer);
            Loan createdLoan = loanRepository.save(loan);
            LoanResponseDTO loanResponseDTO = loanMapper.mapLoanEntityToLoanResponseDTO(createdLoan);
            return loanResponseDTO;
        }
        else {
            return null;
        }
    }
    @Override
    public CardResponseDTO payLoan(LoanPayRequestDTO loanPayRequestDTO) {
        Loan loan = loanRepository.findById(loanPayRequestDTO.getLoanId())
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + loanPayRequestDTO.getLoanId()));
        Card card = cardRepository.findById(loanPayRequestDTO.getCardId())
                .orElseThrow(() -> new CardNotFoundException("Card not found with id: " + loanPayRequestDTO.getCardId()));
        Customer customer = customerRepository.findById(loanPayRequestDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + loanPayRequestDTO.getCustomerId()));
        if (!card.getAccount().getCustomer().equals(customer)) {
            throw new IllegalStateException("Card does not belong to the customer associated with the loan create request.");
        }
        double months = (double) loan.getTermOfCredit().getMonths();
        double amount = loan.getAmount();
        double payCheckForEachMonth = amount / months;
        if (card.checkCardIsActive() && card.getCardType() == CardType.DEBIT && loan.getAmount()>0) {
            if(card.getBalance() >= payCheckForEachMonth){
                double balance = card.getBalance();
                balance = balance - payCheckForEachMonth;
                card.setBalance(balance);
                double loanAmount = loan.getAmount();
                loanAmount = loanAmount - payCheckForEachMonth;
                loan.setAmount(loanAmount);
                loanRepository.save(loan);
            }
        }
        else{
            throw new RuntimeException("Problem for to pay your loan");
        }
        if (loan.getAmount() == 0 ){
            deleteloan(loanPayRequestDTO.getLoanId());
        }
        Card updatedCard = cardRepository.save(card);
        CardResponseDTO cardResponseDTO = cardMapper.mapCardEntityToCardResponseDTO(updatedCard);
        return cardResponseDTO;

    }

    @Override
    public void deleteloan(Long id) {
        loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));
        loanRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllloansOfUser(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        loanRepository.deleteAllByCustomerId(customerId);
    }
}
