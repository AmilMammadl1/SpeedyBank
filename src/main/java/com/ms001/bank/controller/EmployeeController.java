package com.ms001.bank.controller;

import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import com.ms001.bank.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;

    @Operation(summary = "Get Employer", description = "Get Employer")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/developer/employer/all")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployeers() {
        List<EmployeeResponseDTO> allEmployeeResponseDTOS = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployeeResponseDTOS, HttpStatus.OK);
    }

    @Operation(summary = "Get Employer By Id", description = "Get Employer By Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/developer/employer/id/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Create Employer", description = "Create Employer")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/admin/employer/create")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateRequestDTO employeeCreateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.signUp(employeeCreateRequestDTO);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Employer", description = "Update Employer")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/admin/employer/update/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(employeeUpdateRequestDTO, id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete Employer", description = "Delete Employer")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/employer/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeByid(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
