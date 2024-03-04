package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.LoanDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.entity.Loan;
import com.ms001.bank.entity.User;
import com.ms001.bank.repository.LoanRepository;
import com.ms001.bank.repository.UserRepository;
import com.ms001.bank.service.LoanService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    private ModelMapper modelMapper;
    private LoanRepository loanRepository;
    private UserRepository userRepository;

    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanDTO> collect = loans.stream()
                .map(loan -> modelMapper.map(loan, LoanDTO.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<LoanDTO> getAllLoansOfUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Loan> loans = user.getLoans();
        List<LoanDTO> collect = loans.stream()
                .map(loan -> modelMapper.map(loan, LoanDTO.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public LoanDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow();
        LoanDTO loanDTO = modelMapper.map(loan, LoanDTO.class);
        return loanDTO;
    }

    @Override
    public LoanDTO updateLoan(LoanUpdateRequestDTO loanUpdateRequestDTO, Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow();
        loan.setTerm(loan.getTerm());
        loan.setAmount(loan.getAmount());
        Loan savedLoan = loanRepository.save(loan);
        LoanDTO loanDTO = modelMapper.map(savedLoan, LoanDTO.class);
        return loanDTO;
    }

    @Override
    public LoanDTO createLoan(LoanCreateRequestDTO loanCreateRequestDTO) {
        Long userId = loanCreateRequestDTO.getUserId();
        User user = userRepository.findById(userId).orElseThrow();
        Loan loan = new Loan();
        loan.setAmount(loanCreateRequestDTO.getAmount());
        loan.setUser(user);
        loan.setTerm(loanCreateRequestDTO.getTerm());
        Loan savedLoan = loanRepository.save(loan);
        LoanDTO loanDTO = modelMapper.map(savedLoan, LoanDTO.class);
        return loanDTO;
    }

    @Override
    public void deleteloan(Long id) {
        loanRepository.findById(id).orElseThrow();
        loanRepository.deleteById(id);
    }

    @Override
    public void deleteAllloansOfUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Loan> loans = user.getLoans();
        loanRepository.deleteAll(loans);
    }
}
