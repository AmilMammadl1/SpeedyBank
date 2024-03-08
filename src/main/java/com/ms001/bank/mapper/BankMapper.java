package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.ATMCreateRequestDTO;
import com.ms001.bank.dto.request.ATMUpdateRequestDTO;
import com.ms001.bank.dto.request.BankCreateRequestDTO;
import com.ms001.bank.dto.request.BankUpdateRequestDTO;
import com.ms001.bank.dto.response.ATMResponseDTO;
import com.ms001.bank.dto.response.BankResponseDTO;
import com.ms001.bank.entity.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankMapper {
    Bank mapBankCreateRequestDTOToBankEntity(BankCreateRequestDTO bankCreateRequestDTO);
    Bank mapBankUpdateRequestDTOToBankEntity(BankUpdateRequestDTO bankUpdateRequestDTO);
    @Mappings({
            @Mapping(target = "branchIds", source = "bank.branches"),
            @Mapping(target = "atmIds", source = "bank.atms"),
            @Mapping(target = "departmentIds", source = "bank.departments"),
            @Mapping(target = "customerIds", source = "bank.customers"),
    })
    BankResponseDTO mapBankEntityToBankResponseDTO(Bank bank);
    default List<Long> mapBranchListToLongList(List<Branch> branches) {
        return branches.stream()
                .map(Branch::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapATMListToLongList(List<ATM> atms) {
        return atms.stream()
                .map(ATM::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapDepartmentListToLongList(List<Department> departments) {
        return departments.stream()
                .map(Department::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapCustomerListToLongList(List<Customer> customers) {
        return customers.stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
    }
}
