package com.ms001.bank.controller.test;

import com.ms001.bank.dto.request.*;
import com.ms001.bank.dto.response.*;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminContoller {
    private final AccountService accountService;
    private final ATMService atmService;
    private final BankService bankService;
    private final BranchService branchService;
    private final CardService cardService;
    private final CustomerService customerService;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final LoanService loanService;
    private final TransactionService transactionService;

    @PostMapping("/signup-employee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.signUp(employeeCreateRequestDTO);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/bank/{name}")
    public ResponseEntity<BankResponseDTO> getBankById(@PathVariable String name) {
        BankResponseDTO bankResponseDTO = bankService.getBankByName(name);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/bank/all")
    public List<Bank> all() {
        return bankService.getBankAll();
    }

    @PostMapping("/bank/create")
    public ResponseEntity<BankResponseDTO> createBank(@Valid @RequestBody BankCreateRequestDTO bankCreateRequestDTO) {
        BankResponseDTO bankResponseDTO = bankService.createBank(bankCreateRequestDTO);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/bank/update/{name}")
    public ResponseEntity<BankResponseDTO> updateBank(@PathVariable String name, @Valid @RequestBody BankUpdateRequestDTO bankUpdateRequestDTO) {
        BankResponseDTO bankResponseDTO = bankService.updateBank(name, bankUpdateRequestDTO);
        return new ResponseEntity<>(bankResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/bank/delete/{name}")
    public ResponseEntity<Void> deleteBankByid(@PathVariable String name) {
        bankService.deleteBankByid(name);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDTO>> getAllUsers() {
        List<CustomerResponseDTO> allCustomerResponseDtos = customerService.getAllUsers();
        return new ResponseEntity<>(allCustomerResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponseDTO> getUserById(@PathVariable Long id) {
        CustomerResponseDTO customerResponseDTO = customerService.getUserById(id);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @PutMapping("/customer/update//{id}")
    public ResponseEntity<CustomerResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequestDTO updateRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.updateUser(updateRequestDTO,id);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<Void> deleteUserByid(@PathVariable Long id) {
        customerService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/account/all")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> allAccount = accountService.getAllAccount();
        return new ResponseEntity<>(allAccount, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        AccountResponseDTO accountById = accountService.getAccountById(id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @PutMapping("/account/update/{id}")
    public ResponseEntity<AccountResponseDTO> createAccount(@PathVariable Long id, @Valid @RequestBody AccountUpdateRequestDTO accountUpdateRequestDTO) {
        AccountResponseDTO accountById = accountService.updateAccount(accountUpdateRequestDTO, id);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }

    @DeleteMapping("/account/delete/user/{id}")
    public ResponseEntity<Void> deleteAccountByUserId(@PathVariable Long id) {
        accountService.deleteAccountByUserId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/account/delete/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/card/all")
    public ResponseEntity<List<CardResponseDTO>> getAllCards() {
        List<CardResponseDTO> allCardResponseDtos = cardService.getAllCards();
        return new ResponseEntity<>(allCardResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<CardResponseDTO> getCardById(@PathVariable Long id) {
        CardResponseDTO cardResponseDTO = cardService.getCardById(id);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/card/create")
    public ResponseEntity<CardResponseDTO> createCard(@Valid @RequestBody CardCreateRequestDTO cardCreateRequestDTO) {
        CardResponseDTO cardResponseDTO = cardService.createCard(cardCreateRequestDTO);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/card/update/{id}")
    public ResponseEntity<CardResponseDTO> updateCard(@PathVariable Long id, @Valid @RequestBody CardUpdateRequestDTO cardUpdateRequestDTO) {
        CardResponseDTO cardResponseDTO = cardService.updateCard(cardUpdateRequestDTO, id);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }
    @PutMapping("/card/transaction")
    public ResponseEntity<CardResponseDTO> CardTransaction(@Valid @RequestBody ProcessTransactionDTO processTransactionDTO) {
        CardResponseDTO cardResponseDTO = cardService.CardTransaction(processTransactionDTO);
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/card/delete/{id}")
    public ResponseEntity<Void> deleteCardByid(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department/all")
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<DepartmentResponseDTO> allDepartments = departmentService.getAllDepartments();
        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/department/create")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Valid @RequestBody DepartmentCreateRequestDTO departmentCreateRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.createDepartment(departmentCreateRequestDTO);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/deparmet/update/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentUpdateRequestDTO departmentUpdateRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.updateDepartment(departmentUpdateRequestDTO, id);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/departmentdelete/{id}")
    public ResponseEntity<Void> deleteDepartmentByid(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/employee/all")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployeers() {
        List<EmployeeResponseDTO> allEmployeeResponseDTOS = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployeeResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

//    @PostMapping("/employee/create")
    public ResponseEntity<EmployeeResponseDTO> createEmployee2(@Valid @RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.signUp(employeeCreateRequestDTO);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/employee/update/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(employeeUpdateRequestDTO, id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeByid(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/branch/all")
    public ResponseEntity<List<BranchResponseDTO>> getAllBranchs() {
        List<BranchResponseDTO> allBranch = branchService.getAllBranch();
        return new ResponseEntity<>(allBranch, HttpStatus.OK);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<BranchResponseDTO> getBranchById(@PathVariable Long id) {
        BranchResponseDTO branchResponseDTO = branchService.getBranchByid(id);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/branch/create")
    public ResponseEntity<BranchResponseDTO> createBranch(@Valid @RequestBody BranchCreateRequestDTO branchCreateRequestDTO) {
        BranchResponseDTO branchResponseDTO = branchService.createBranch(branchCreateRequestDTO);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/branch/update/{id}")
    public ResponseEntity<BranchResponseDTO> updateBranch(@PathVariable Long id,@Valid @RequestBody BranchUpdateRequestDTO branchUpdateRequestDTO) {
        BranchResponseDTO branchResponseDTO = branchService.updateBranch(id, branchUpdateRequestDTO);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/branch/delete/{id}")
    public ResponseEntity<Void> deleteBranchByid(@PathVariable Long id) {
        branchService.deleteBranchByid(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/atm/all")
    public ResponseEntity<List<ATMResponseDTO>> getAllATMs() {
        List<ATMResponseDTO> allATM = atmService.getAllATM();
        return new ResponseEntity<>(allATM, HttpStatus.OK);
    }

    @GetMapping("/atm/{id}")
    public ResponseEntity<ATMResponseDTO> getATMById(@PathVariable Long id) {
        ATMResponseDTO ATMResponseDTO = atmService.getATMById(id);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/atm/create")
    public ResponseEntity<ATMResponseDTO> createATM(@Valid @RequestBody ATMCreateRequestDTO atmCreateRequestDTO) {
        ATMResponseDTO ATMResponseDTO = atmService.createATM(atmCreateRequestDTO);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/atm/update/{id}")
    public ResponseEntity<ATMResponseDTO> updateATM(@PathVariable Long id, @Valid @RequestBody ATMUpdateRequestDTO atmUpdateRequestDTO) {
        ATMResponseDTO ATMResponseDTO = atmService.updateATM(id, atmUpdateRequestDTO);
        return new ResponseEntity<>(ATMResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/atm/delete/{id}")
    public ResponseEntity<Void> deleteATMByid(@PathVariable Long id) {
        atmService.deleteATM(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/transaction/all")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<TransactionResponseDTO> allTransactionResponseDTOS = transactionService.getAllTransaction();
        return new ResponseEntity<>(allTransactionResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable String name) {
        TransactionResponseDTO transactionResponseDTO = transactionService.getTransactionById(name);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }
    @PostMapping("/transaction/create")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionCreateRequestDTO transactionCreateRequestDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionService.createTransaction(transactionCreateRequestDTO);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/transaction/delete/{name}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable String name) {
        transactionService.deleteTransaction(name);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/loan/all")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans() {
        List<LoanResponseDTO> allLoanResponseDTOS = loanService.getAllLoans();
        return new ResponseEntity<>(allLoanResponseDTOS, HttpStatus.OK);
    }
    @GetMapping("/loan/user/all/{id}")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoansOfUser(@PathVariable Long id) {
        List<LoanResponseDTO> allLoanResponseDTOS = loanService.getAllLoansOfUser(id);
        return new ResponseEntity<>(allLoanResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/loan/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id) {
        LoanResponseDTO loanResponseDTO = loanService.getLoanById(id);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/loan/create")
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody LoanCreateRequestDTO loanCreateRequestDTO) {
        LoanResponseDTO loanResponseDTO = loanService.createLoan(loanCreateRequestDTO);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/loan/update/{id}")
    public ResponseEntity<LoanResponseDTO> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanUpdateRequestDTO updateRequestDTO) {
        LoanResponseDTO loanResponseDTO = loanService.updateLoan(updateRequestDTO,id);
        return new ResponseEntity<>(loanResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/loan/delete/{id}")
    public ResponseEntity<Void> deleteLoanByid(@PathVariable Long id) {
        loanService.deleteloan(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/loan/user/all/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllLoansOfUser(@PathVariable Long id) {
        loanService.deleteAllloansOfUser(id);
    }
}



