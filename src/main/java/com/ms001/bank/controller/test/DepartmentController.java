package com.ms001.bank.controller.test;

import com.ms001.bank.dto.request.DepartmentUpdateRequestDTO;
import com.ms001.bank.dto.response.DepartmentResponseDTO;
import com.ms001.bank.dto.request.DepartmentCreateRequestDTO;
import com.ms001.bank.service.DepartmentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<DepartmentResponseDTO> allDepartments = departmentService.getAllDepartments();
        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.OK);
    }

//    @PostMapping("/create")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Valid @RequestBody DepartmentCreateRequestDTO departmentCreateRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.createDepartment(departmentCreateRequestDTO);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.CREATED);
    }

//    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentUpdateRequestDTO departmentUpdateRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.updateDepartment(departmentUpdateRequestDTO, id);
        return new ResponseEntity<>(departmentResponseDTO, HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartmentByid(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
