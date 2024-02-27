package com.ms001.bank.dto.request;

import com.ms001.bank.constant.CardType;

import java.util.Date;
import java.util.List;

public class CardRequestDTO {
    private String cardNumber;
    private Date expirationDate;
    private CardType cardType;
    private boolean isActive;
    private double balanceCard;
    private Long accountId;  // Assuming you want to include account ID in the request DTO
    private List<Long> transactionIds;
}
