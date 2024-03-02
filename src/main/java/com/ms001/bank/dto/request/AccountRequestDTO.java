package com.ms001.bank.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
    private double balanceTotal;
    private boolean isActive;
    private Long userId;
    private List<Long> cardIds;

}
