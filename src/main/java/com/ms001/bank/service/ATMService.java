package com.ms001.bank.service;

import com.ms001.bank.dto.ATMDTO;
import com.ms001.bank.dto.request.ATMRequestDTO;

import java.util.List;

public interface ATMService {
    ATMDTO getATMById(Long id);
    List<ATMDTO> getAllATM();
    ATMDTO createATM(ATMRequestDTO atmRequestDTO);
    ATMDTO updateATM(Long id,ATMRequestDTO atmRequestDTO);
    void deleteATM(Long id);


}
