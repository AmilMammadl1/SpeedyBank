package com.ms001.bank.dto.request;

import com.ms001.bank.constant.SupportedServicesType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanCreateRequestDTO {
    @NotNull(message = "Amount field cannot be null")
    private Double amount;
    @NotNull(message = "Term field cannot be null")
    private Long term;
    @NotNull(message = "Customer field cannot be null")
    private Long customerId;
}
