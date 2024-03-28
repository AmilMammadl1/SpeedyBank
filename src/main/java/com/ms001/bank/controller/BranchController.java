package com.ms001.bank.controller;

import com.ms001.bank.dto.request.BranchUpdateRequestDTO;
import com.ms001.bank.dto.response.BranchResponseDTO;
import com.ms001.bank.dto.request.BranchCreateRequestDTO;
import com.ms001.bank.service.BranchService;
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
public class BranchController {
    private BranchService branchService;

    @GetMapping("/branch/all")
    public ResponseEntity<List<BranchResponseDTO>> getAllBranchs() {
        List<BranchResponseDTO> allBranch = branchService.getAllBranch();
        return new ResponseEntity<>(allBranch, HttpStatus.OK);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<BranchResponseDTO> getBranchById(@PathVariable Long id) {
        BranchResponseDTO branchResponseDTO = branchService.getBranchByid(id);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Create Branch", description = "Create Branch")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/developer/branch/create")
    public ResponseEntity<BranchResponseDTO> createBranch(@Valid @RequestBody BranchCreateRequestDTO branchCreateRequestDTO) {
        BranchResponseDTO branchResponseDTO = branchService.createBranch(branchCreateRequestDTO);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Branch", description = "Update Branch")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/developer//branch/update/{id}")
    public ResponseEntity<BranchResponseDTO> updateBranch(@PathVariable Long id, @Valid @RequestBody BranchUpdateRequestDTO branchUpdateRequestDTO) {
        BranchResponseDTO branchResponseDTO = branchService.updateBranch(id, branchUpdateRequestDTO);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete Branch", description = "Delete Branch")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/branch/delete/{id}")
    public ResponseEntity<Void> deleteBranchByid(@PathVariable Long id) {
        branchService.deleteBranchByid(id);
        return ResponseEntity.noContent().build();
    }
}
