package com.ms001.bank.service;

import com.ms001.bank.dto.response.AccountResponseDTO;
import com.ms001.bank.dto.request.AccountRequestDTO;

import java.util.List;

public interface AccountService {
    List<AccountResponseDTO> getAllAccount();
    AccountResponseDTO getAccountById(Long id);
    AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO, Long id);
    void deleteAccount(Long id);
    void deleteAccountByUserId(Long userId);

}
