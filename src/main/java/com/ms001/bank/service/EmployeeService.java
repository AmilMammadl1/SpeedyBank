package com.ms001.bank.service;

import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long id);

    EmployeeResponseDTO updateEmployee(EmployeeUpdateRequestDTO employeeUpdateRequestDTO, Long id);

    EmployeeResponseDTO signUp(EmployeeCreateRequestDTO employeeCreateRequestDTO);

    void deleteEmployee(Long id);

    UserDetailsService userDetailsService();

}
