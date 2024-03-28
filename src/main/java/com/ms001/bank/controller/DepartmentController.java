package com.ms001.bank.controller;

import com.ms001.bank.dto.request.DepartmentUpdateRequestDTO;
import com.ms001.bank.dto.response.DepartmentResponseDTO;
import com.ms001.bank.dto.request.DepartmentCreateRequestDTO;
import com.ms001.bank.service.DepartmentService;
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
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping("/department/all")
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<DepartmentResponseDTO> allDepartments = departmentService.getAllDepartments();
        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    @GetMapping("/department/id/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Create Department", description = "Create Department")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/developer/department/create")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Valid @RequestBody DepartmentCreateRequestDTO departmentCreateRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.createDepartment(departmentCreateRequestDTO);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.CREATED);
    }
    @Operation(summary = "Update Department", description = "Update Department")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/developer/department/update/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentUpdateRequestDTO departmentUpdateRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.updateDepartment(departmentUpdateRequestDTO, id);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Delete Department", description = "Delete Department")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/department/delete/{id}")
    public ResponseEntity<Void> deleteDepartmentByid(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
