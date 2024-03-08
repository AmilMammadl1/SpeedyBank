package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.entity.Loan;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.repository.LoanRepository;
import com.ms001.bank.repository.CustomerRepository;
import com.ms001.bank.service.LoanService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    private ModelMapper modelMapper;
    private LoanRepository loanRepository;
    private CustomerRepository customerRepository;

    @Override
    public List<LoanResponseDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanResponseDTO> collect = loans.stream()
                .map(loan -> modelMapper.map(loan, LoanResponseDTO.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<LoanResponseDTO> getAllLoansOfUser(Long userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow();
        List<Loan> loans = customer.getLoans();
        List<LoanResponseDTO> collect = loans.stream()
                .map(loan -> modelMapper.map(loan, LoanResponseDTO.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public LoanResponseDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow();
        LoanResponseDTO loanResponseDTO = modelMapper.map(loan, LoanResponseDTO.class);
        return loanResponseDTO;
    }

    @Override
    public LoanResponseDTO updateLoan(LoanUpdateRequestDTO loanUpdateRequestDTO, Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow();
        loan.setTerm(loan.getTerm());
        loan.setAmount(loan.getAmount());
        Loan savedLoan = loanRepository.save(loan);
        LoanResponseDTO loanResponseDTO = modelMapper.map(savedLoan, LoanResponseDTO.class);
        return loanResponseDTO;
    }

    @Override
    public LoanResponseDTO createLoan(LoanCreateRequestDTO loanCreateRequestDTO) {
        Long userId = loanCreateRequestDTO.getUserId();
        Customer customer = customerRepository.findById(userId).orElseThrow();
        Loan loan = new Loan();
        loan.setAmount(loanCreateRequestDTO.getAmount());
        loan.setCustomer(customer);
        loan.setTerm(loanCreateRequestDTO.getTerm());
        Loan savedLoan = loanRepository.save(loan);
        LoanResponseDTO loanResponseDTO = modelMapper.map(savedLoan, LoanResponseDTO.class);
        return loanResponseDTO;
    }

    @Override
    public void deleteloan(Long id) {
        loanRepository.findById(id).orElseThrow();
        loanRepository.deleteById(id);
    }

    @Override
    public void deleteAllloansOfUser(Long userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow();
        List<Loan> loans = customer.getLoans();
        loanRepository.deleteAll(loans);
    }
}
