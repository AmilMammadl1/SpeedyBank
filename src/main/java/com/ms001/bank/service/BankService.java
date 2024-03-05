package com.ms001.bank.service;

import com.ms001.bank.dto.BankDTO;
import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;

public interface BankService {
    BankDTO getBankkByName(String name);
    BankDTO createBank(BankCreateRequestDTO bankCreateRequestDTO);
    BankDTO updateBank(String name, BankUpdateRequestDTO bankUpdateRequestDTO);
    void deleteBankByid(String name);
}
