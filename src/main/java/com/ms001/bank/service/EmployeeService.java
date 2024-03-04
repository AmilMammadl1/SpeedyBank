package com.ms001.bank.service;

import com.ms001.bank.dto.DepartmentDTO;
import com.ms001.bank.dto.EmployeeDTO;
import com.ms001.bank.dto.request.DepartmentRequestDTO;
import com.ms001.bank.dto.request.EmployeeRequestDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, Long id);
    EmployeeDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    void deleteEmployee(Long id);
}
