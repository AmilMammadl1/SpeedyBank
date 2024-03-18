package com.ms001.bank.controller;

import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import com.ms001.bank.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployeers() {
        List<EmployeeResponseDTO> allEmployeeResponseDTOS = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployeeResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.signUp(employeeCreateRequestDTO);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(employeeUpdateRequestDTO, id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeByid(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
