package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import com.ms001.bank.entity.Department;
import com.ms001.bank.entity.Employee;
import com.ms001.bank.mapper.EmployeeMapper;
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
    private EmployeeMapper employeeMapper;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> all = employeeRepository.findAll();
        List<EmployeeResponseDTO> collect = all.stream()
                .map(employee -> employeeMapper.mapEmployeeEntityToEmployeeResponseDTO(employee))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not fount with id: " + id));
        EmployeeResponseDTO employeeResponseDTO = employeeMapper.mapEmployeeEntityToEmployeeResponseDTO(employee);
        return employeeResponseDTO;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        Employee employee = employeeMapper.mapEmployeeCreateRequestDTOToEmployeeEntity(employeeCreateRequestDTO);
        Department department = departmentRepository.findById(employeeCreateRequestDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: "
                        + employeeCreateRequestDTO.getDepartmentId()));
        employee.setDepartment(department);
        Employee createdEmployee = employeeRepository.save(employee);
        EmployeeResponseDTO employeeResponseDTO = employeeMapper.mapEmployeeEntityToEmployeeResponseDTO(createdEmployee);
        return employeeResponseDTO;
    }

    @Override
    public EmployeeResponseDTO updateEmployee(EmployeeUpdateRequestDTO employeeUpdateRequestDTO, Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        Department department = departmentRepository.findById(employeeUpdateRequestDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + employeeUpdateRequestDTO.getDepartmentId()));
        employee.setDepartment(department);
//        employee = employeeMapper.mapEmployeeUpdateRequestDTOToEmployeeEntity(employeeUpdateRequestDTO);
        employee.setSalary(employeeUpdateRequestDTO.getSalary());
        employee.setEmail(employeeUpdateRequestDTO.getEmail());
        employee.setLastName(employeeUpdateRequestDTO.getLastName());
        employee.setFatherName(employeeUpdateRequestDTO.getFatherName());
        employee.setFirstName(employeeUpdateRequestDTO.getFirstName());
        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeResponseDTO employeeResponseDTO = employeeMapper.mapEmployeeEntityToEmployeeResponseDTO(updatedEmployee);
        return employeeResponseDTO;
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not fount with id: " + id));
        employeeRepository.deleteById(id);
    }
}
