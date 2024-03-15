package com.ms001.bank.controller;

import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerSignInRequest;
import com.ms001.bank.dto.request.RefreshTokenRequest;
import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.response.JwtAuthenticationResponse;
import com.ms001.bank.service.CustomerService;
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
    @PostMapping("/signup")
    public ResponseEntity<CustomerResponseDTO> createUser(@Valid @RequestBody CustomerCreateRequestDTO customerCreateRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.signUp(customerCreateRequestDTO);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> User(@Valid @RequestBody CustomerSignInRequest customerSignInRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = customerService.signIn(customerSignInRequest);
        return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = customerService.refreshToken(refreshTokenRequest);
        return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
    }
}
