package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.ATMCreateRequestDTO;
import com.ms001.bank.dto.request.ATMUpdateRequestDTO;
import com.ms001.bank.dto.response.ATMResponseDTO;
import com.ms001.bank.entity.ATM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ATMMapper {
    ATM mapATMCreateRequestDTOToATMEntity(ATMCreateRequestDTO atmCreateRequestDTO);
    ATM mapATMUpdateRequestDTOToATMEntity(ATMUpdateRequestDTO updateRequestDTO);
    @Mappings({
            @Mapping(target = "bankName", source = "atm.bank.name")
    })
    ATMResponseDTO mapATMEntityToATMResponseDTO(ATM atm);
}
