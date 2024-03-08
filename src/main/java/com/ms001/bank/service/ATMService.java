package com.ms001.bank.service;

import com.ms001.bank.dto.request.ATMCreateRequestDTO;
import com.ms001.bank.dto.request.ATMUpdateRequestDTO;
import com.ms001.bank.dto.response.ATMResponseDTO;
import java.util.List;

public interface ATMService {
    ATMResponseDTO getATMById(Long id);
    List<ATMResponseDTO> getAllATM();
    ATMResponseDTO createATM(ATMCreateRequestDTO atmCreateRequestDTO);
    ATMResponseDTO updateATM(Long id, ATMUpdateRequestDTO updateRequestDTO);
    void deleteATM(Long id);


}
