package com.ms001.bank.controller;

import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;
import com.ms001.bank.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@AllArgsConstructor
//@RequestMapping("/api")
public class CustomerController {
    private CustomerService customerService;
    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDTO>> getAllUsers() {
        List<CustomerResponseDTO> allCustomerResponseDtos = customerService.getAllUsers();
        return new ResponseEntity<>(allCustomerResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<CustomerResponseDTO> getUserById(@PathVariable Long id) {
        CustomerResponseDTO customerResponseDTO = customerService.getUserById(id);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }


    @PutMapping("/update/user/{id}")
    public ResponseEntity<CustomerResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequestDTO updateRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.updateUser(updateRequestDTO,id);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<Void> deleteUserByid(@PathVariable Long id) {
        customerService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
