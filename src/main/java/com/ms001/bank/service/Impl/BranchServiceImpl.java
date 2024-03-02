package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.BranchDTO;
import com.ms001.bank.dto.request.BranchRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Branch;
import com.ms001.bank.repository.ATMRepository;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.repository.BranchRepository;
import com.ms001.bank.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {
    private ModelMapper modelMapper;
    private BranchRepository branchRepository;
    private BankRepository bankRepository;
    @Override
    public List<BranchDTO> getAllBranch() {
        List<Branch> all = branchRepository.findAll();
        List<BranchDTO> collect = all.stream().map(branch -> modelMapper.map(branch, BranchDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public BranchDTO getBranchByid(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id + " for the specified bank"));
        BranchDTO map = modelMapper.map(branch, BranchDTO.class);
        return map;
    }

    @Override
    public BranchDTO createBranch(BranchRequestDTO branchRequestDTO) {
        Bank bank = bankRepository.findById(branchRequestDTO.getBankName())
                .orElseThrow(() -> new RuntimeException("Bank not found with name: " + branchRequestDTO.getBankName()));

        Branch branch = new Branch();
        branch.setBank(bank);
        branch.setBranchName(branchRequestDTO.getBranchName());
        branch.setLocation(branchRequestDTO.getLocation());
        Branch save = branchRepository.save(branch);
        BranchDTO map = modelMapper.map(save, BranchDTO.class);
        return map;
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchRequestDTO branchRequestDTO) {
        Bank bank = bankRepository.findById(branchRequestDTO.getBankName())
                .orElseThrow(() -> new RuntimeException("Bank not found with name: " + branchRequestDTO.getBankName()));
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found with name: " + branchRequestDTO.getBankName()));

        if (branch != null) {
            branch = new Branch();
            branch.setBank(bank);
            branch.setBranchName(branchRequestDTO.getBranchName());
            branch.setLocation(branchRequestDTO.getLocation());
            Branch save = branchRepository.save(branch);
            BranchDTO map = modelMapper.map(save, BranchDTO.class);
            return map;
        }
        return null;
    }
    @Override
    public void deleteBranchByid(Long id) {
        branchRepository.deleteById(id);
    }
}
