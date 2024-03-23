package com.ms001.bank.dto.request;

import lombok.Data;

@Data
public class LoanPayRequestDTO {
    Long cardId;
    Long loanId;
    Long customerId;
}
