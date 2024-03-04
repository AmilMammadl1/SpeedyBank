package com.ms001.bank.dto.request;

import com.ms001.bank.constant.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardUpdateRequestDTO {
    private CardType cardType;
    private Boolean isActive;
}
