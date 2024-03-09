package com.ms001.bank.repository;

import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Loan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Loan l WHERE l.customer.id = :customerId")
    void deleteAllByCustomerId(@Param("customerId") Long customerId);

}
