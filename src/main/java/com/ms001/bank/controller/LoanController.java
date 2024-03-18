package com.ms001.bank.controller;

import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.service.LoanService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/loan")
public class LoanController {
    private LoanService loanService;

    @GetMapping("/all")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans() {
        List<LoanResponseDTO> allLoanResponseDTOS = loanService.getAllLoans();
        return new ResponseEntity<>(allLoanResponseDTOS, HttpStatus.OK);
    }
    @GetMapping("/user/all/{id}")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoansOfUser(@PathVariable Long id) {
        List<LoanResponseDTO> allLoanResponseDTOS = loanService.getAllLoansOfUser(id);
        return new ResponseEntity<>(allLoanResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id) {
        LoanResponseDTO loanResponseDTO = loanService.getLoanById(id);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody LoanCreateRequestDTO loanCreateRequestDTO) {
        LoanResponseDTO loanResponseDTO = loanService.createLoan(loanCreateRequestDTO);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoanResponseDTO> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanUpdateRequestDTO updateRequestDTO) {
        LoanResponseDTO loanResponseDTO = loanService.updateLoan(updateRequestDTO,id);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoanByid(@PathVariable Long id) {
        loanService.deleteloan(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/user/all/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllLoansOfUser(@PathVariable Long id) {
        loanService.deleteAllloansOfUser(id);
    }

}
