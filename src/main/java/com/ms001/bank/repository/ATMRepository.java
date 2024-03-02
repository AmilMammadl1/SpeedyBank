package com.ms001.bank.repository;

import com.ms001.bank.entity.ATM;
import com.ms001.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ATMRepository extends JpaRepository<ATM,Long> {
    Optional<ATM> findByIdAndBank(Long atmId, Bank byIdBank);
    List<ATM> findByBank(Bank bankName);


}
