package com.ms001.bank.controller;

import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LoanController {
    private LoanService loanService;
    @Operation(summary = "Get Loan", description = "Get Loan")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/loan/all")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans() {
        List<LoanResponseDTO> allLoanResponseDTOS = loanService.getAllLoans();
        return new ResponseEntity<>(allLoanResponseDTOS, HttpStatus.OK);
    }
    @Operation(summary = "Get Loans Of User", description = "Get Loan Of User")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/customer/all/{id}")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoansOfUser(@PathVariable Long id) {
        List<LoanResponseDTO> allLoanResponseDTOS = loanService.getAllLoansOfUser(id);
        return new ResponseEntity<>(allLoanResponseDTOS, HttpStatus.OK);
    }
    @Operation(summary = "Get Loan By Id", description = "Get Loan By Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/loan/id/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id) {
        LoanResponseDTO loanResponseDTO = loanService.getLoanById(id);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Create Loan", description = "Create Loan")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/customer/loan/create")
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody LoanCreateRequestDTO loanCreateRequestDTO) {
        LoanResponseDTO loanResponseDTO = loanService.createLoan(loanCreateRequestDTO);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.CREATED);
    }

//    @PutMapping("/update/{id}")
    public ResponseEntity<LoanResponseDTO> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanUpdateRequestDTO updateRequestDTO) {
        LoanResponseDTO loanResponseDTO = loanService.updateLoan(updateRequestDTO,id);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Delete Loan", description = "Delete Loan")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/loan/delete/{id}")
    public ResponseEntity<Void> deleteLoanByid(@PathVariable Long id) {
        loanService.deleteloan(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Delete All Loans Of User", description = "Delete All Loans Of User")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/all/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllLoansOfUser(@PathVariable Long id) {
        loanService.deleteAllloansOfUser(id);
    }

}
