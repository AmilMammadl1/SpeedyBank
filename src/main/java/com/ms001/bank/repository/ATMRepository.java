package com.ms001.bank.repository;

import com.ms001.bank.entity.ATM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATMRepository extends JpaRepository<ATM,Long> {
}
