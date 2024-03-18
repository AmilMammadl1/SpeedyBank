package com.ms001.bank.controller.test;

import com.ms001.bank.dto.request.*;
import com.ms001.bank.dto.response.*;
import com.ms001.bank.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/developer")
@RequiredArgsConstructor
public class DeveloperContoller {
    private final AccountService accountService;
    private final ATMService atmService;
    private final BranchService branchService;
    private final CardService cardService;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @PutMapping("/atm/update/{id}")
    public ResponseEntity<ATMResponseDTO> updateATM(@PathVariable Long id, @Valid @RequestBody ATMUpdateRequestDTO atmUpdateRequestDTO) {
        ATMResponseDTO ATMResponseDTO = atmService.updateATM(id, atmUpdateRequestDTO);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/branch/update/{id}")
    public ResponseEntity<BranchResponseDTO> updateBranch(@PathVariable Long id,@Valid @RequestBody BranchUpdateRequestDTO branchUpdateRequestDTO) {
        BranchResponseDTO branchResponseDTO = branchService.updateBranch(id, branchUpdateRequestDTO);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }
    @PutMapping("/employee/update/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(employeeUpdateRequestDTO, id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }
    @PutMapping("/departmet/update/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentUpdateRequestDTO departmentUpdateRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.updateDepartment(departmentUpdateRequestDTO, id);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/account/all")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> allAccount = accountService.getAllAccount();
        return new ResponseEntity<>(allAccount, HttpStatus.OK);
    }

    @GetMapping("account-customer/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        AccountResponseDTO accountById = accountService.getAccountById(id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }
    @PutMapping("/account/update/{id}")
    public ResponseEntity<AccountResponseDTO> createAccount(@PathVariable Long id, @Valid @RequestBody AccountUpdateRequestDTO accountUpdateRequestDTO) {
        AccountResponseDTO accountById = accountService.updateAccount(accountUpdateRequestDTO, id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @PutMapping("/card/update/{id}")
    public ResponseEntity<CardResponseDTO> updateCard(@PathVariable Long id, @Valid @RequestBody CardUpdateRequestDTO cardUpdateRequestDTO) {
        CardResponseDTO cardResponseDTO = cardService.updateCard(cardUpdateRequestDTO, id);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }
}
