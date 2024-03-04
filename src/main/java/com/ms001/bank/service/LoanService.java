package com.ms001.bank.service;

import com.ms001.bank.dto.LoanDTO;
import com.ms001.bank.dto.UserDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.dto.request.UserCreateRequestDTO;
import com.ms001.bank.dto.request.UserUpdateRequestDTO;

import java.util.List;

public interface LoanService {
    List<LoanDTO> getAllLoans();
    List<LoanDTO> getAllLoansOfUser(Long userId);
    LoanDTO getLoanById(Long id);
    LoanDTO updateLoan(LoanUpdateRequestDTO loanUpdateRequestDTO, Long id);
    LoanDTO createLoan(LoanCreateRequestDTO loanCreateRequestDTO);
    void deleteloan(Long id);
    void deleteAllloansOfUser(Long userId);

}
