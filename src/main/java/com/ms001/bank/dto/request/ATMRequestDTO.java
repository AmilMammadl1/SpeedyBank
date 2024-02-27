package com.ms001.bank.dto.request;

import com.ms001.bank.constant.SupportedServicesType;

public class ATMRequestDTO {
    private String location;
    private String status;
    private SupportedServicesType supportedServices;
    private Long bankId;
}
