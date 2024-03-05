package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.BankDTO;
import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.service.BankService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    private ModelMapper modelMapper;
    private BankRepository bankRepository;
    @Override
    public BankDTO getBankkByName(String name) {
        Bank bank = bankRepository.findById(name).orElseThrow();
        BankDTO bankDTO = modelMapper.map(bank, BankDTO.class);
        return bankDTO;
    }

    @Override
    public BankDTO createBank(BankCreateRequestDTO bankCreateRequestDTO) {
        Bank bank = new Bank();
        bank.setName(bankCreateRequestDTO.getName());
        Bank savedBank = bankRepository.save(bank);
        BankDTO bankDTO = modelMapper.map(savedBank, BankDTO.class);
        return bankDTO;
    }

    @Override
    public BankDTO updateBank(String name, BankUpdateRequestDTO bankUpdateRequestDTO) {
        Bank bank = bankRepository.findById(name).orElseThrow();
        bank.setName(bankUpdateRequestDTO.getName());
        Bank savedBank = bankRepository.save(bank);
        BankDTO bankDTO = modelMapper.map(savedBank, BankDTO.class);
        return bankDTO;
    }
    @Override
    public void deleteBankByid(String name) {
        Bank bank = bankRepository.findById(name).orElseThrow();
        bankRepository.deleteById(name);
    }
}
