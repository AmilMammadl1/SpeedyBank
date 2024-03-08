package com.ms001.bank.service;

import com.ms001.bank.dto.response.DepartmentResponseDTO;
import com.ms001.bank.dto.request.DepartmentRequestDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponseDTO> getAllDepartments();
    DepartmentResponseDTO getDepartmentById(Long id);
    DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO, Long id);
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);
    void deleteDepartment(Long id);
}
