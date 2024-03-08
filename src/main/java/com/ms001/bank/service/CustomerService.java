package com.ms001.bank.service;

import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> getAllUsers();
    CustomerResponseDTO getUserById(Long id);
    CustomerResponseDTO updateUser(CustomerUpdateRequestDTO customerUpdateRequestDTO, Long id);
    CustomerResponseDTO createUser(CustomerCreateRequestDTO customerCreateRequestDTO);
    void deleteUser(Long id);
}
