package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.BankResponseDTO;
import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.exception.BankNotFoundException;
import com.ms001.bank.mapper.BankMapper;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.service.BankService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    private BankMapper bankMapper;
    private BankRepository bankRepository;

    @Override
    public BankResponseDTO getBankByName(String name) {
        Bank bank = bankRepository.findById(name)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with name: " + name));
        BankResponseDTO bankResponseDTO = bankMapper.mapBankEntityToBankResponseDTO(bank);
        return bankResponseDTO;
    }

    public List<Bank> getBankAll() {
        return bankRepository.findAll();
    }

    @Override
    public BankResponseDTO createBank(BankCreateRequestDTO bankCreateRequestDTO) {
        Bank bank = bankMapper.mapBankCreateRequestDTOToBankEntity(bankCreateRequestDTO);
        Bank createdBank = bankRepository.save(bank);
        BankResponseDTO bankResponseDTO = bankMapper.mapBankEntityToBankResponseDTO(createdBank);
        return bankResponseDTO;
    }

    @Override
    public BankResponseDTO updateBank(String name, BankUpdateRequestDTO bankUpdateRequestDTO) {
        Bank bank = bankRepository.findById(name)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with name: " + name));
        bank = bankMapper.mapBankUpdateRequestDTOToBankEntity(bankUpdateRequestDTO);
        Bank updatedBank = bankRepository.save(bank);
        BankResponseDTO bankResponseDTO = bankMapper.mapBankEntityToBankResponseDTO(updatedBank);
        return bankResponseDTO;
    }

    @Override
    public void deleteBankByid(String name) {
        Bank bank = bankRepository.findById(name)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with name: " + name));
        bankRepository.deleteById(name);
    }
}
