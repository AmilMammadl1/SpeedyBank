package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.AccountResponseDTO;
import com.ms001.bank.dto.request.AccountUpdateRequestDTO;
import com.ms001.bank.entity.Account;
import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.exception.AccountNotFoundException;
import com.ms001.bank.mapper.AccountMapper;
import com.ms001.bank.repository.AccountRepository;
import com.ms001.bank.repository.CardRepository;
import com.ms001.bank.repository.CustomerRepository;
import com.ms001.bank.service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardRepository cardRepository;


    @Override
    public List<AccountResponseDTO> getAllAccount() {
        List<Account> all = accountRepository.findAll();

        List<AccountResponseDTO> accountResponseDTOS = all.stream()
                .map(account -> {
                    double totalBalance = account.getCards().stream()
                            .mapToDouble(Card::getBalance)
                            .sum();
                    account.setBalanceTotal(totalBalance);

                    return accountMapper.mapAccountEntityToAccountResponseDTO(account);
                })
                .collect(Collectors.toList());

        return accountResponseDTOS;
    }


    @Override
    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not find with id: " + id));
        AccountResponseDTO accountResponseDTO = accountMapper.mapAccountEntityToAccountResponseDTO(account);
        double totalBalance = account.getCards().stream()
                .mapToDouble(Card::getBalance)
                .sum();
        account.setBalanceTotal(totalBalance);
        return accountResponseDTO;
    }

    @Override
    public AccountResponseDTO updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO, Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));

        // Update the account properties based on the AccountRequestDTO
        account.setActive(accountUpdateRequestDTO.isActive());

        // Save the updated account to the repository
        Account updatedAccount = accountRepository.save(account);

        // Map the updated account to AccountDTO and return
        return accountMapper.mapAccountEntityToAccountResponseDTO(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not find with id: " + id));
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAccountByUserId(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
        accountRepository.deleteById(id);
    }
}
