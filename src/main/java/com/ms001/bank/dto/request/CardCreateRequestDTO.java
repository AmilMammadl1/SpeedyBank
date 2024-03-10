package com.ms001.bank.dto.request;

import com.ms001.bank.constant.CardType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateRequestDTO {
    @NotNull(message = "Card type field cannot be null")
    private CardType cardType;
    @NotNull(message = "Account field cannot be null")
    private Long accountId;
}
