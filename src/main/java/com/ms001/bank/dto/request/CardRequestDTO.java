package com.ms001.bank.dto.request;

import com.ms001.bank.constant.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDTO {
    private CardType cardType;
    private Long accountId;
}
