package com.ms001.bank.controller.test;

import com.ms001.bank.dto.request.CardCreateRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;
import com.ms001.bank.dto.request.ProcessTransactionDTO;
import com.ms001.bank.dto.response.CardResponseDTO;
import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.service.CardService;
import com.ms001.bank.service.CustomerService;
import com.ms001.bank.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerContoller {
    private final CustomerService customerService;
    private final CardService cardService;
    private final LoanService loanService;
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getUserById(@PathVariable Long id) {
        CustomerResponseDTO customerResponseDTO = customerService.getUserById(id);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequestDTO updateRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.updateUser(updateRequestDTO,id);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/card/{id}")
    public ResponseEntity <List<CardResponseDTO>> getCardsByUserId(@PathVariable Long id) {
        List<CardResponseDTO> cardResponseDTO = cardService.getAllCardsByAccountId(id);
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
    @GetMapping("all/loans-user/{userId}")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoansOfUser(@PathVariable Long userId) {
        List<LoanResponseDTO> allLoanResponseDTOS = loanService.getAllLoansOfUser(userId);
        return new ResponseEntity<>(allLoanResponseDTOS, HttpStatus.OK);
    }

}
