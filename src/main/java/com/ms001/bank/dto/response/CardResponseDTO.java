package com.ms001.bank.dto.response;

import com.ms001.bank.constant.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDTO {
    private Long id;
    private CardType cardType;
    private double balance;
    private Long accountId;
}
