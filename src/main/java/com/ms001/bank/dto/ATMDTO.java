package com.ms001.bank.dto;

import com.ms001.bank.constant.SupportedServicesType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ATMDTO {
    private Long id;
    private String location;
    private SupportedServicesType supportedServices;
    private Long bankId;
}
