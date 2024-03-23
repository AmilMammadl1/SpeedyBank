package com.ms001.bank.service;

import com.ms001.bank.dto.request.LoanPayRequestDTO;
import com.ms001.bank.dto.response.CardResponseDTO;
import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;

import java.util.List;

public interface LoanService {
    List<LoanResponseDTO> getAllLoans();
    List<LoanResponseDTO> getAllLoansOfUser(Long userId);
    LoanResponseDTO getLoanById(Long id);
    LoanResponseDTO updateLoan(LoanUpdateRequestDTO loanUpdateRequestDTO, Long id);
    LoanResponseDTO createLoan(LoanCreateRequestDTO loanCreateRequestDTO);
    CardResponseDTO payLoan(LoanPayRequestDTO loanPayRequestDTO);
    void deleteloan(Long id);
    void deleteAllloansOfUser(Long userId);

}
