package com.ms001.bank.repository;

import com.ms001.bank.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    @Query("SELECT c FROM Card c JOIN c.account a WHERE a.user.id = :userId")
    List<Card> findByUserId(@Param("userId") Long userId);

}
