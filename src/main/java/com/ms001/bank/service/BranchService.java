package com.ms001.bank.service;

import com.ms001.bank.dto.request.BranchUpdateRequestDTO;
import com.ms001.bank.dto.response.BranchResponseDTO;
import com.ms001.bank.dto.request.BranchCreateRequestDTO;

import java.util.List;

public interface BranchService {
    List<BranchResponseDTO> getAllBranch();
    BranchResponseDTO getBranchByid(Long id);
    BranchResponseDTO createBranch(BranchCreateRequestDTO branchCreateRequestDTO);
    BranchResponseDTO updateBranch(Long id, BranchUpdateRequestDTO branchUpdateRequestDTO);
    void deleteBranchByid(Long id);
}
