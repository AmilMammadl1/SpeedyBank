package com.ms001.bank.dto.request;

import com.ms001.bank.constant.SupportedServicesType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanCreateRequestDTO {
    private Double amount;
    private String term;
    private Long userId;
}
