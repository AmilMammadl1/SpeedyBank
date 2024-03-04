package com.ms001.bank.repository;

import com.ms001.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("DELETE FROM Account a WHERE a.user.id = :userId")
    void deleteAccountByUserId(Long userId);
}
