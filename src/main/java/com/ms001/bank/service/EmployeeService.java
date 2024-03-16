package com.ms001.bank.service;

import com.ms001.bank.dto.request.EmployeeSignInRequest;
import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import com.ms001.bank.dto.response.JwtAuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    JwtAuthenticationResponse signIn(EmployeeSignInRequest employeeSignInRequest);

    EmployeeResponseDTO getEmployeeById(Long id);

    EmployeeResponseDTO updateEmployee(EmployeeUpdateRequestDTO employeeUpdateRequestDTO, Long id);

    EmployeeResponseDTO signUp(EmployeeCreateRequestDTO employeeCreateRequestDTO);

    void deleteEmployee(Long id);

//    UserDetailsService userDetailsService();

}
