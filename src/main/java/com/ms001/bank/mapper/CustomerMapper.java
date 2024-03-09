package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;
import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.entity.Employee;
import com.ms001.bank.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer mapCustomerCreateRequestDTOToCustomerEntity(CustomerCreateRequestDTO customerCreateRequestDTO);
    Customer mapCustomerUpdateRequestDTOToCustomerEntity(CustomerUpdateRequestDTO customerUpdateRequestDTO);
    @Mappings({
            @Mapping(target = "bankName", source = "customer.bank.name"),
            @Mapping(target = "loanIds", source = "customer.loans"),
            @Mapping(target = "accountId", source = "customer.account.id")


    })
    CustomerResponseDTO mapCustomerEntityToEmployeeResponseDTO(Customer customer);
    default List<Long> mapEmployeeListToLongList(List<Loan> loans) {
        return loans.stream()
                .map(Loan::getId)
                .collect(Collectors.toList());
    }

}
