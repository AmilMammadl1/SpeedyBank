package com.ms001.bank.service;

import com.ms001.bank.dto.CardDTO;
import com.ms001.bank.dto.DepartmentDTO;
import com.ms001.bank.dto.request.CardRequestDTO;
import com.ms001.bank.dto.request.DepartmentRequestDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentById(Long id);
    DepartmentDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO, Long id);
    DepartmentDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);
    void deleteDepartment(Long id);
}
