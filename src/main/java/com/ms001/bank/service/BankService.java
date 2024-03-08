package com.ms001.bank.service;

import com.ms001.bank.dto.response.BankResponseDTO;
import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;
import com.ms001.bank.entity.Bank;

import java.util.List;

public interface BankService {
    BankResponseDTO getBankByName(String name);

     List<Bank> getBankAll();

    BankResponseDTO createBank(BankCreateRequestDTO bankCreateRequestDTO);

    BankResponseDTO updateBank(String name, BankUpdateRequestDTO bankUpdateRequestDTO);

    void deleteBankByid(String name);
}
