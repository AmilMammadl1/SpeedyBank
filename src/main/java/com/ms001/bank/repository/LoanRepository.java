package com.ms001.bank.repository;

import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {

}
