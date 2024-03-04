package com.ms001.bank.service;

import com.ms001.bank.dto.AccountDTO;
import com.ms001.bank.dto.DepartmentDTO;
import com.ms001.bank.dto.request.AccountRequestDTO;
import com.ms001.bank.dto.request.DepartmentRequestDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccount();
    AccountDTO getAccountById(Long id);
    AccountDTO updateAccount(AccountRequestDTO accountRequestDTO, Long id);
    void deleteAccount(Long id);
    void deleteAccountByUserId(Long userId);

}
