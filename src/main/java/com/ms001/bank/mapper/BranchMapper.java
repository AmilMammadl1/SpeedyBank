package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;
import com.ms001.bank.dto.request.BranchCreateRequestDTO;
import com.ms001.bank.dto.request.BranchUpdateRequestDTO;
import com.ms001.bank.dto.response.BankResponseDTO;
import com.ms001.bank.dto.response.BranchResponseDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {
    Branch mapBranchCreateRequestDTOToBranchEntity(BranchCreateRequestDTO branchCreateRequestDTO);
//    @Mappings({
//            @Mapping(target = "branch.bank.name", source = "branchUpdateRequestDTObankName")
//    })
    Branch mapBranchUpdateRequestDTOToBranchEntity(BranchUpdateRequestDTO branchUpdateRequestDTO);
    @Mappings({
            @Mapping(target = "bankName", source = "branch.bank.name")
    })
    BranchResponseDTO mapBranchEntityToBranchResponseDTO(Branch branch);
}
