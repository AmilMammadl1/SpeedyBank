package com.ms001.bank.dto.request;

import com.ms001.bank.constant.SupportedServicesType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ATMRequestDTO {
    private String location;
    private List<SupportedServicesType> supportedServices;
    private String bankName;
}
