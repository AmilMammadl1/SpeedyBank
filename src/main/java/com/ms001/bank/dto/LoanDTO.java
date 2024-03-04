package com.ms001.bank.dto;

import com.ms001.bank.constant.SupportedServicesType;
import com.ms001.bank.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
    private Long id;
    private Double amount;
    private String term;
    private Long userId;
}
