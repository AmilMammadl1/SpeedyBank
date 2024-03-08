package com.ms001.bank.service;

import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.request.EmployeeRequestDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, Long id);
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    void deleteEmployee(Long id);
}
