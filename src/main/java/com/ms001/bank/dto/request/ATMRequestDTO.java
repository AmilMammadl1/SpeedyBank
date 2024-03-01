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
public class ATMRequestDTO {
    private String location;
    private String status;
    private SupportedServicesType supportedServices;
    private Long bankId;
}
