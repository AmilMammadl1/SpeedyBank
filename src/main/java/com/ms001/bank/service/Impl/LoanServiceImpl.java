package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.entity.Loan;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.exception.CustomerNotFoundException;
import com.ms001.bank.exception.LoanNotFoundException;
import com.ms001.bank.mapper.LoanMapper;
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
    private LoanRepository loanRepository;
    private CustomerRepository customerRepository;

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

        loan.setTerm(loanUpdateRequestDTO.getTerm());
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
        loan.setCustomer(customer);
        Loan createdLoan = loanRepository.save(loan);
        LoanResponseDTO loanResponseDTO = loanMapper.mapLoanEntityToLoanResponseDTO(createdLoan);
        return loanResponseDTO;
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
