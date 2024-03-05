package com.ms001.bank.controller;

import com.ms001.bank.dto.ATMDTO;
import com.ms001.bank.dto.BranchDTO;
import com.ms001.bank.dto.request.ATMRequestDTO;
import com.ms001.bank.dto.request.BranchRequestDTO;
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
    public ResponseEntity<List<BranchDTO>> getAllBranchs() {
        List<BranchDTO> allBranch = branchService.getAllBranch();
        return new ResponseEntity<>(allBranch, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) {
        BranchDTO branchDTO = branchService.getBranchByid(id);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchRequestDTO branchRequestDTO) {
        BranchDTO branchDTO = branchService.createBranch(branchRequestDTO);
        return new ResponseEntity<>(branchDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchRequestDTO branchRequestDTO) {
        BranchDTO branchDTO = branchService.updateBranch(id, branchRequestDTO);
        return new ResponseEntity<>(branchDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBranchByid(@PathVariable Long id) {
        branchService.deleteBranchByid(id);
        return ResponseEntity.noContent().build();
    }
}
