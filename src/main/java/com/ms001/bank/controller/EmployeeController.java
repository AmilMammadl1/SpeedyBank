package com.ms001.bank.controller;

import com.ms001.bank.dto.BranchDTO;
import com.ms001.bank.dto.EmployeeDTO;
import com.ms001.bank.dto.request.BranchRequestDTO;
import com.ms001.bank.dto.request.EmployeeRequestDTO;
import com.ms001.bank.service.BranchService;
import com.ms001.bank.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeers() {
        List<EmployeeDTO> allEmployeeDTOs = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployeeDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeDTO employeeDTO = employeeService.createEmployee(employeeRequestDTO);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeDTO employeeDTO = employeeService.updateEmployee(employeeRequestDTO,id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeByid(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
