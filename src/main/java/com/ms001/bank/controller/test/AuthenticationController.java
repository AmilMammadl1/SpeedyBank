package com.ms001.bank.controller.test;

import com.ms001.bank.dto.request.*;
import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.response.JwtAuthenticationResponse;
import com.ms001.bank.service.CustomerService;
import com.ms001.bank.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    @PostMapping("/signup-customer")
    public ResponseEntity<CustomerResponseDTO> createUser(@Valid @RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.signUp(customerCreateRequestDTO);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/signin-customer")
    public ResponseEntity<JwtAuthenticationResponse> Customer(@Valid @RequestBody CustomerSignInRequest customerSignInRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = customerService.signIn(customerSignInRequest);
        return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
    }
    @PostMapping("/signup-employee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.signUp(employeeCreateRequestDTO);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/signin-employee")
    public ResponseEntity<JwtAuthenticationResponse> Employee(@Valid @RequestBody EmployeeSignInRequest employeeSignInRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = employeeService.signIn(employeeSignInRequest);
        return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
    }
//    @PostMapping("/refresh")
//    public ResponseEntity<JwtAuthenticationResponse> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
//        JwtAuthenticationResponse jwtAuthenticationResponse = customerService.refreshToken(refreshTokenRequest);
//        return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
//    }
}
