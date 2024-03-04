package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.DepartmentDTO;
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
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> all = departmentRepository.findAll();
        List<DepartmentDTO> collect = all.stream().map(department -> modelMapper.map(department, DepartmentDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        DepartmentDTO map = modelMapper.map(department, DepartmentDTO.class);
        return map;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        Department department = new Department();

        Bank bank = bankRepository.findById(departmentRequestDTO.getBankName())
                .orElseThrow(() -> new RuntimeException("Bank not found with specified name: " + departmentRequestDTO.getBankName()));
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
        DepartmentDTO map = modelMapper.map(savedDepartment, DepartmentDTO.class);
        return map;
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO, Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        Bank bank = bankRepository.findById(departmentRequestDTO.getBankName())
                .orElseThrow(() -> new RuntimeException("Bank not found with specified name: " + departmentRequestDTO.getBankName()));
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
        DepartmentDTO map = modelMapper.map(savedDepartment, DepartmentDTO.class);
        return map;
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        departmentRepository.deleteById(id);
    }
}
