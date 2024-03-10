package com.ms001.bank.dto.request;

import com.ms001.bank.constant.CardType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardUpdateRequestDTO {
    @NotNull(message = "Card type field cannot be null")
    private CardType cardType;
    @NotNull(message = "Check Activity field cannot be null")
    private Boolean isActive;
}
