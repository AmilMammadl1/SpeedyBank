package com.ms001.bank.service;

import com.ms001.bank.dto.request.CustomerSignInRequest;
import com.ms001.bank.dto.request.RefreshTokenRequest;
import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;
import com.ms001.bank.dto.response.JwtAuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> getAllUsers();

    CustomerResponseDTO getUserById(Long id);

    CustomerResponseDTO updateUser(CustomerUpdateRequestDTO customerUpdateRequestDTO, Long id);

    CustomerResponseDTO signUp(CustomerCreateRequestDTO customerCreateRequestDTO);

    void deleteUser(Long id);
    UserDetailsService userDetailsService();
    JwtAuthenticationResponse signIn(CustomerSignInRequest customerSignInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
