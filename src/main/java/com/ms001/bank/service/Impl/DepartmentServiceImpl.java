package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.request.DepartmentUpdateRequestDTO;
import com.ms001.bank.dto.response.DepartmentResponseDTO;
import com.ms001.bank.dto.request.DepartmentCreateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Department;
import com.ms001.bank.entity.Employee;
import com.ms001.bank.exception.BankNotFoundException;
import com.ms001.bank.exception.DepartmentNotFoundException;
import com.ms001.bank.mapper.DepartmentMapper;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.repository.DepartmentRepository;
import com.ms001.bank.repository.EmployeeRepository;
import com.ms001.bank.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentMapper departmentMapper;
    private DepartmentRepository departmentRepository;
    private BankRepository bankRepository;

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> all = departmentRepository.findAll();
        List<DepartmentResponseDTO> collect = all.stream().map(department -> departmentMapper.mapDepartmentEntityToDepartmentResponseDTO(department)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
        DepartmentResponseDTO departmentResponseDTO = departmentMapper.mapDepartmentEntityToDepartmentResponseDTO(department);
        return departmentResponseDTO;
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentCreateRequestDTO departmentCreateRequestDTO) {
        Department department = departmentMapper.mapDepartmentCreateRequestDTOToDepartmentEntity(departmentCreateRequestDTO);

        Bank bank = bankRepository.findById(departmentCreateRequestDTO.getBankName())
                .orElseThrow(() -> new BankNotFoundException("Bank not found with specified name: " + departmentCreateRequestDTO.getBankName()));
        department.setBank(bank);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponseDTO departmentResponseDTO = departmentMapper.mapDepartmentEntityToDepartmentResponseDTO(department);
        return departmentResponseDTO;
    }

    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentUpdateRequestDTO departmentUpdateRequestDTO, Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setName(departmentUpdateRequestDTO.getName());
        department.setDescription(departmentUpdateRequestDTO.getDescription());
        Department updatedDepartment = departmentRepository.save(department);
        DepartmentResponseDTO departmentResponseDTO = departmentMapper.mapDepartmentEntityToDepartmentResponseDTO(updatedDepartment);
        return departmentResponseDTO;
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        departmentRepository.deleteById(id);
    }
}

