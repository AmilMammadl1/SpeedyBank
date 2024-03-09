package com.ms001.bank.service;

import com.ms001.bank.dto.request.DepartmentUpdateRequestDTO;
import com.ms001.bank.dto.response.DepartmentResponseDTO;
import com.ms001.bank.dto.request.DepartmentCreateRequestDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponseDTO> getAllDepartments();
    DepartmentResponseDTO getDepartmentById(Long id);
    DepartmentResponseDTO updateDepartment(DepartmentUpdateRequestDTO departmentUpdateRequestDTO, Long id);
    DepartmentResponseDTO createDepartment(DepartmentCreateRequestDTO departmentCreateRequestDTO);
    void deleteDepartment(Long id);
}
