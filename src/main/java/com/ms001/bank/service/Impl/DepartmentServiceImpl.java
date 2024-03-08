package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.DepartmentResponseDTO;
import com.ms001.bank.dto.request.DepartmentRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Department;
import com.ms001.bank.entity.Employee;
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
    private ModelMapper modelMapper;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private BankRepository bankRepository;

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> all = departmentRepository.findAll();
        List<DepartmentResponseDTO> collect = all.stream().map(department -> modelMapper.map(department, DepartmentResponseDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        DepartmentResponseDTO map = modelMapper.map(department, DepartmentResponseDTO.class);
        return map;
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        Department department = new Department();

        Bank bank = bankRepository.findById(departmentRequestDTO.getBankName()).orElseThrow(() -> new RuntimeException("Bank not found with specified name: " + departmentRequestDTO.getBankName()));
        List<Long> employeeIds = departmentRequestDTO.getEmployeeIds();
        List<Employee> employees = new ArrayList<>();

        for (Long employeeId : employeeIds) {
            Optional<Employee> employeeById = employeeRepository.findById(employeeId);
            if (employeeById.isPresent()) {
                Employee employee = employeeById.get();
                employees.add(employee);
            }
        }

        department.setName(departmentRequestDTO.getName());
        department.setDescription(departmentRequestDTO.getDescription());
        department.setBank(bank);
        department.setEmployees(employees);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponseDTO map = modelMapper.map(savedDepartment, DepartmentResponseDTO.class);
        return map;
    }

    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO, Long id) {

        Bank bank = bankRepository.findById(departmentRequestDTO.getBankName()).orElseThrow(() -> new RuntimeException("Bank not found with specified name: " + departmentRequestDTO.getBankName()));
//        Department department = departmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        List<Department> departments = bank.getDepartments();
        Department department = null;
        if (id >= 1) {
            department = departments.get((int) (id - 1));
        }
        List<Long> employeeIds = departmentRequestDTO.getEmployeeIds();
        List<Employee> employees = new ArrayList<>();

        for (Long employeeId : employeeIds) {
            Optional<Employee> employeeById = employeeRepository.findById(employeeId);
            if (employeeById.isPresent()) {
                Employee employee = employeeById.get();
                employees.add(employee);
            }
        }
        if (department != null) {

            department.setName(departmentRequestDTO.getName());
            department.setDescription(departmentRequestDTO.getDescription());
            department.setBank(bank);
            department.setEmployees(employees);
            Department savedDepartment = departmentRepository.save(department);
            DepartmentResponseDTO map = modelMapper.map(savedDepartment, DepartmentResponseDTO.class);
            return map;
        }
        return null;
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        departmentRepository.deleteById(id);
    }
}

