package com.ms001.bank.dto.request;

import com.ms001.bank.constant.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateRequestDTO {
    private CardType cardType;
    private Long accountId;
}
