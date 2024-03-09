package com.ms001.bank.service;

import com.ms001.bank.dto.response.AccountResponseDTO;
import com.ms001.bank.dto.request.AccountUpdateRequestDTO;

import java.util.List;

public interface AccountService {
    List<AccountResponseDTO> getAllAccount();
    AccountResponseDTO getAccountById(Long id);
    AccountResponseDTO updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO, Long id);
    void deleteAccount(Long id);
    void deleteAccountByUserId(Long id);

}
