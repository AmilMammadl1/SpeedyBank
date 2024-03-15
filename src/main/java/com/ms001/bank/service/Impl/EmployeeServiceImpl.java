package com.ms001.bank.service.Impl;

import com.ms001.bank.constant.Role;
import com.ms001.bank.dto.request.*;
import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.response.JwtAuthenticationResponse;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.entity.Department;
import com.ms001.bank.entity.Employee;
import com.ms001.bank.exception.BankNotFoundException;
import com.ms001.bank.exception.CustomerNotFoundException;
import com.ms001.bank.exception.DepartmentNotFoundException;
import com.ms001.bank.exception.EmployeeNotFoundException;
import com.ms001.bank.mapper.EmployeeMapper;
import com.ms001.bank.repository.DepartmentRepository;
import com.ms001.bank.repository.EmployeeRepository;
import com.ms001.bank.service.EmployeeService;
import com.ms001.bank.service.jwt.JWTService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeMapper employeeMapper;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

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
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            //loadUserByUsername method returns an instance of UserDetails, which is another interface in Spring Security. UserDetails represents a user's core information (such as username, password, and authorities) and is used by Spring Security to perform authentication and authorization.
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return employeeRepository.findByEmail(username)
                        .orElseThrow(() -> new EmployeeNotFoundException("Employeer not found with email: " + username));
            }
        };
    }
    @Override
    public EmployeeResponseDTO signUp(EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        Employee employee = employeeMapper.mapEmployeeCreateRequestDTOToEmployeeEntity(employeeCreateRequestDTO);
        Department department = departmentRepository.findById(employeeCreateRequestDTO.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: "
                        + employeeCreateRequestDTO.getDepartmentId()));
        employee.setDepartment(department);
        employee.setPasswrd(passwordEncoder.encode(employeeCreateRequestDTO.getPassword()));
        employee.setRole(Role.DEVELOPER);
        Employee createdEmployee = employeeRepository.save(employee);
        EmployeeResponseDTO employeeResponseDTO = employeeMapper.mapEmployeeEntityToEmployeeResponseDTO(createdEmployee);
        return employeeResponseDTO;
    }
    public JwtAuthenticationResponse signIn(EmployeeSignInRequest employeeSignInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employeeSignInRequest.getEmail(),
                employeeSignInRequest.getPassword()));
        var employee = employeeRepository.findByEmail(employeeSignInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Username or password is invalid"));
        var jwt = jwtService.generateToken(employee);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), employee);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }
}
