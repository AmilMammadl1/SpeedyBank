package com.ms001.bank.service;

import com.ms001.bank.dto.BranchDTO;
import com.ms001.bank.dto.request.BranchRequestDTO;

import java.util.List;

public interface BranchService {
    List<BranchDTO> getAllBranch();
    BranchDTO getBranchByid(Long id);
    BranchDTO createBranch(BranchRequestDTO branchRequestDTO);
    BranchDTO updateBranch(Long id, BranchRequestDTO branchRequestDTO);
    void deleteBranchByid(Long id);



}
