package com.ms001.bank.controller;

import com.ms001.bank.dto.request.BranchUpdateRequestDTO;
import com.ms001.bank.dto.response.BranchResponseDTO;
import com.ms001.bank.dto.request.BranchCreateRequestDTO;
import com.ms001.bank.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/branch")
public class BranchController {
    private BranchService branchService;

    @GetMapping("/all")
    public ResponseEntity<List<BranchResponseDTO>> getAllBranchs() {
        List<BranchResponseDTO> allBranch = branchService.getAllBranch();
        return new ResponseEntity<>(allBranch, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDTO> getBranchById(@PathVariable Long id) {
        BranchResponseDTO branchResponseDTO = branchService.getBranchByid(id);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BranchResponseDTO> createBranch(@RequestBody BranchCreateRequestDTO branchCreateRequestDTO) {
        BranchResponseDTO branchResponseDTO = branchService.createBranch(branchCreateRequestDTO);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BranchResponseDTO> updateBranch(@PathVariable Long id, @RequestBody BranchUpdateRequestDTO branchUpdateRequestDTO) {
        BranchResponseDTO branchResponseDTO = branchService.updateBranch(id, branchUpdateRequestDTO);
        return new ResponseEntity<>(branchResponseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBranchByid(@PathVariable Long id) {
        branchService.deleteBranchByid(id);
        return ResponseEntity.noContent().build();
    }
}
