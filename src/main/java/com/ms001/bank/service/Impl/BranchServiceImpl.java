package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.request.BranchUpdateRequestDTO;
import com.ms001.bank.dto.response.BranchResponseDTO;
import com.ms001.bank.dto.request.BranchCreateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Branch;
import com.ms001.bank.exception.BranchNotFoundException;
import com.ms001.bank.mapper.BranchMapper;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.repository.BranchRepository;
import com.ms001.bank.service.BranchService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements BranchService {
    private BranchMapper branchMapper;
    private BranchRepository branchRepository;
    private BankRepository bankRepository;

    @Override
    public List<BranchResponseDTO> getAllBranch() {
        List<Branch> all = branchRepository.findAll();
        List<BranchResponseDTO> branchResponseDTOS = all.stream()
                .map(branch -> branchMapper.mapBranchEntityToBranchResponseDTO(branch))
                .collect(Collectors.toList());
        return branchResponseDTOS;
    }

    @Override
    public BranchResponseDTO getBranchByid(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException("Branch not found with id: " + id));
        BranchResponseDTO branchResponseDTO = branchMapper.mapBranchEntityToBranchResponseDTO(branch);
        return branchResponseDTO;
    }

    @Override
    public BranchResponseDTO createBranch(BranchCreateRequestDTO branchCreateRequestDTO) {
        Bank bank = bankRepository.findById(branchCreateRequestDTO.getBankName())
                .orElseThrow(() -> new RuntimeException("Bank not found with name: " + branchCreateRequestDTO.getBankName()));
        Branch branch = branchMapper.mapBranchCreateRequestDTOToBranchEntity(branchCreateRequestDTO);
        branch.setBank(bank);
        Branch createdBranch = branchRepository.save(branch);

        BranchResponseDTO branchResponseDTO = branchMapper.mapBranchEntityToBranchResponseDTO(createdBranch);
        return branchResponseDTO;
    }

    @Override
    public BranchResponseDTO updateBranch(Long id, BranchUpdateRequestDTO branchUpdateRequestDTO) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException("Branch not found with id: " + id));
        branch.setBranchName(branchUpdateRequestDTO.getBranchName());
        branch.setBranchName(branchUpdateRequestDTO.getBranchName());

        Branch updatedBranch = branchRepository.save(branch);
        BranchResponseDTO branchResponseDTO = branchMapper.mapBranchEntityToBranchResponseDTO(updatedBranch);
        return branchResponseDTO;
    }

    @Override
    public void deleteBranchByid(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException("Branch not found with id: " + id));
        branchRepository.deleteById(id);
    }
}
