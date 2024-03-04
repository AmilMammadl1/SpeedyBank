package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.EmployeeDTO;
import com.ms001.bank.dto.request.EmployeeRequestDTO;
import com.ms001.bank.entity.Department;
import com.ms001.bank.entity.Employee;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.repository.DepartmentRepository;
import com.ms001.bank.repository.EmployeeRepository;
import com.ms001.bank.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private ModelMapper modelMapper;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> all = employeeRepository.findAll();
        List<EmployeeDTO> collect = all.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not fount with id: " + id));
        EmployeeDTO map = modelMapper.map(employee, EmployeeDTO.class);
        return map;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        Department department = departmentRepository.findById(employeeRequestDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + employeeRequestDTO.getDepartmentId()));
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setSalary(employee.getSalary());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setFatherName(employeeRequestDTO.getFatherName());
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO map = modelMapper.map(savedEmployee, EmployeeDTO.class);
        return map;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        Department department = departmentRepository.findById(employeeRequestDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + employeeRequestDTO.getDepartmentId()));
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setSalary(employee.getSalary());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setFatherName(employeeRequestDTO.getFatherName());
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO map = modelMapper.map(savedEmployee, EmployeeDTO.class);
        return map;
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not fount with id: " + id));
        employeeRepository.deleteById(id);
    }
}
