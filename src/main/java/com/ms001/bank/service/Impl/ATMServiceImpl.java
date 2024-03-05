package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.ATMDTO;
import com.ms001.bank.dto.request.ATMRequestDTO;
import com.ms001.bank.entity.ATM;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.repository.ATMRepository;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.service.ATMService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ATMServiceImpl implements ATMService {
    private ModelMapper modelMapper;
    private ATMRepository atmRepository;
    private BankRepository bankRepository;

    @Override
    public ATMDTO getATMById(Long id) {
        ATM byId = atmRepository.findById(id).orElseThrow(() -> new RuntimeException("ATM not found with id: " + id));
        ATMDTO map = modelMapper.map(byId, ATMDTO.class);
        return map;
    }

    @Override
    public List<ATMDTO> getAllATM() {
        List<ATM> all = atmRepository.findAll();
        List<ATMDTO> collect = all.stream().map(atm -> modelMapper.map(atm, ATMDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ATMDTO createATM(ATMRequestDTO atmRequestDTO) {
        String name = atmRequestDTO.getBankName();
        Bank byId = bankRepository.findById(name).orElseThrow();
        ATM atm = new ATM(atmRequestDTO.getLocation(), byId);

        ATM save = atmRepository.save(atm);
        ATMDTO map = modelMapper.map(save, ATMDTO.class);
        return map;
    }

    @Override
    public ATMDTO updateATM(Long atmId, ATMRequestDTO atmRequestDTO) {
        Bank byIdBank = bankRepository.findById(atmRequestDTO.getBankName())
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + atmRequestDTO.getBankName()));

        List<ATM> atms = byIdBank.getAtms();
        ATM atm = atms.get((int) (atmId - 1));
//        ATM atm = atmRepository.findById(atmId)
//                .orElseThrow(() -> new RuntimeException("ATM not found with id: " + atmId + " for the specified bank"));
        if (atm != null) {
            atm.setLocation(atmRequestDTO.getLocation());
            atm.setSupportedServices(atmRequestDTO.getSupportedServices());
            atm.setBank(byIdBank);
            ATM save = atmRepository.save(atm);
            ATMDTO atmdto = modelMapper.map(save, ATMDTO.class);
            return atmdto;
        }
        return null;
    }
        @Override
        public void deleteATM (Long id){
            ATM byId = atmRepository.findById(id).orElseThrow(() -> new RuntimeException("ATM not found with id: " + id));
            atmRepository.deleteById(id);
        }
    }
