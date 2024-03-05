package com.ms001.bank.controller;

import com.ms001.bank.dto.BranchDTO;
import com.ms001.bank.dto.DepartmentDTO;
import com.ms001.bank.dto.request.BranchRequestDTO;
import com.ms001.bank.dto.request.DepartmentRequestDTO;
import com.ms001.bank.service.BranchService;
import com.ms001.bank.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> allDepartments = departmentService.getAllDepartments();
        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        DepartmentDTO departmentDTO = departmentService.createDepartment(departmentRequestDTO);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequestDTO departmentRequestDTO) {
        DepartmentDTO departmentDTO = departmentService.updateDepartment(departmentRequestDTO, id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartmentByid(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
