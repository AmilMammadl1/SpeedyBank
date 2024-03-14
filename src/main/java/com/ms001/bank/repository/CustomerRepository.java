package com.ms001.bank.repository;

import com.ms001.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByFinCode(String fincode);
}
