package com.ms001.bank.controller;

import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;
import com.ms001.bank.service.CustomerService;
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
public class CustomerController {
    private CustomerService customerService;
    @Operation(summary = "Get All Customer", description = "Get All Customer")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/all/customer")
    public ResponseEntity<List<CustomerResponseDTO>> getAllUsers() {
        List<CustomerResponseDTO> allCustomerResponseDtos = customerService.getAllUsers();
        return new ResponseEntity<>(allCustomerResponseDtos, HttpStatus.OK);
    }
    @Operation(summary = "Get Customer", description = "Get Customer")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/customer/id/{id}")
    public ResponseEntity<CustomerResponseDTO> getUserById(@PathVariable Long id) {
        CustomerResponseDTO customerResponseDTO = customerService.getUserById(id);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Update Customer", description = "Update Customer")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/customer/update/{id}")
    public ResponseEntity<CustomerResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequestDTO updateRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.updateUser(updateRequestDTO,id);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Delete Customer", description = "Delete Customer")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/customer/delete/{id}")
    public ResponseEntity<Void> deleteUserByid(@PathVariable Long id) {
        customerService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
