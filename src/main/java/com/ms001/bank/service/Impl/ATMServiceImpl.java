package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.request.ATMCreateRequestDTO;
import com.ms001.bank.dto.request.ATMUpdateRequestDTO;
import com.ms001.bank.dto.response.ATMResponseDTO;
import com.ms001.bank.entity.ATM;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.exception.ATMNotFoundException;
import com.ms001.bank.exception.BankNotFoundException;
import com.ms001.bank.mapper.ATMMapper;
import com.ms001.bank.repository.ATMRepository;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.service.ATMService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ATMServiceImpl implements ATMService {
    private ATMMapper atmMapper;
    private ATMRepository atmRepository;
    private BankRepository bankRepository;

    @Override
    public ATMResponseDTO getATMById(Long id) {
        ATM atm = atmRepository.findById(id).orElseThrow(() -> new ATMNotFoundException("ATM not found with id: " + id));
        ATMResponseDTO atmResponseDTO = atmMapper.mapATMEntityToATMResponseDTO(atm);
        return atmResponseDTO;
    }

    @Override
    public List<ATMResponseDTO> getAllATM() {
        List<ATM> all = atmRepository.findAll();
        List<ATMResponseDTO> collect = all.stream()
                .map(atm -> atmMapper
                        .mapATMEntityToATMResponseDTO(atm)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ATMResponseDTO createATM(ATMCreateRequestDTO atmCreateRequestDTO) {
        String bankName = atmCreateRequestDTO.getBankName();
        Bank bank = bankRepository.findById(bankName)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with name: " + bankName));

        ATM atm = atmMapper.mapATMCreateRequestDTOToATMEntity(atmCreateRequestDTO);
        atm.setBank(bank);  // Set the bank property after mapping
        ATM createdAtm = atmRepository.save(atm);

        ATMResponseDTO atmResponseDTO = atmMapper.mapATMEntityToATMResponseDTO(createdAtm);

        return atmResponseDTO;
    }


    @Override
    public ATMResponseDTO updateATM(Long atmId, ATMUpdateRequestDTO atmUpdateRequestDTO) {

        ATM atm = atmRepository.findById(atmId)
                .orElseThrow(() -> new ATMNotFoundException("ATM not found with id: " + atmId + " for the specified bank"));
        atm.setLocation(atmUpdateRequestDTO.getLocation());
        atm.setSupportedServices(atmUpdateRequestDTO.getSupportedServices());
        ATM updatedATM = atmRepository.save(atm);
        ATMResponseDTO atmResponseDTO = atmMapper.mapATMEntityToATMResponseDTO(updatedATM);
        return atmResponseDTO;
    }

    @Override
    public void deleteATM(Long id) {
        ATM byId = atmRepository.findById(id).orElseThrow(() -> new ATMNotFoundException("ATM not found with id: " + id));
        atmRepository.deleteById(id);
    }
}
