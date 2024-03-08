package com.ms001.bank.dto.response;

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
public class ATMResponseDTO {
    private Long id;
    private String location;
    private List<SupportedServicesType> supportedServices;
    private String bankName;
}
