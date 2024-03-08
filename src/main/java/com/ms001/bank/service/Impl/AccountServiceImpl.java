package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.AccountResponseDTO;
import com.ms001.bank.dto.request.AccountRequestDTO;
import com.ms001.bank.entity.Account;
import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.repository.AccountRepository;
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
    private ModelMapper modelMapper;
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public List<AccountResponseDTO> getAllAccount() {
        List<Account> all = accountRepository.findAll();
        List<AccountResponseDTO> collect = all.stream().map(account -> modelMapper.map(account, AccountResponseDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not find with id: " + id));
        AccountResponseDTO map = modelMapper.map(account, AccountResponseDTO.class);
        return map;
    }

    @Override
    public AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO, Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        // Update the account properties based on the AccountRequestDTO
        account.setActive(accountRequestDTO.isActive());

        // Calculate the total balance from all cards and set it to balanceTotal
        double totalBalance = account.getCards().stream()
                .mapToDouble(Card::getBalance)
                .sum();
        account.setBalanceTotal(totalBalance);

        // Save the updated account to the repository
        Account updatedAccount = accountRepository.save(account);

        // Map the updated account to AccountDTO and return
        return modelMapper.map(updatedAccount, AccountResponseDTO.class);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not find with id: " + id));
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAccountByUserId(Long userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow();
        Account account = customer.getAccount();
        if(account!=null){
            Long id = account.getId();
            accountRepository.deleteById(id);
        }
    }
}
