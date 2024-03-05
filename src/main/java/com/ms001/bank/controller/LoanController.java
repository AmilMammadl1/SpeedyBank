package com.ms001.bank.controller;

import com.ms001.bank.dto.EmployeeDTO;
import com.ms001.bank.dto.LoanDTO;
import com.ms001.bank.dto.request.EmployeeRequestDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.service.EmployeeService;
import com.ms001.bank.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/loan")
public class LoanController {
    private LoanService loanService;

    @GetMapping("/all")
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        List<LoanDTO> allLoanDTOs = loanService.getAllLoans();
        return new ResponseEntity<>(allLoanDTOs, HttpStatus.OK);
    }
    @GetMapping("/user/all/{id}")
    public ResponseEntity<List<LoanDTO>> getAllLoansOfUser(@PathVariable Long id) {
        List<LoanDTO> allLoanDTOs = loanService.getAllLoansOfUser(id);
        return new ResponseEntity<>(allLoanDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        LoanDTO loanDTO = loanService.getLoanById(id);
        return new ResponseEntity<>(loanDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanCreateRequestDTO loanCreateRequestDTO) {
        LoanDTO loanDTO = loanService.createLoan(loanCreateRequestDTO);
        return new ResponseEntity<>(loanDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @RequestBody LoanUpdateRequestDTO updateRequestDTO) {
        LoanDTO loanDTO = loanService.updateLoan(updateRequestDTO,id);
        return new ResponseEntity<>(loanDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoanByid(@PathVariable Long id) {
        loanService.deleteloan(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/user/all/delete/{id}")
    public ResponseEntity<Void> deleteAllLoansOfUser(@PathVariable Long id) {
        loanService.deleteAllloansOfUser(id);
        return ResponseEntity.noContent().build();
    }

}
