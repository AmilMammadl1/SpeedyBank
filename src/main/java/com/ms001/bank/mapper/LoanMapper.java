package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.entity.Employee;
import com.ms001.bank.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {
    Loan mapLoanCreateRequestDTOToEmployeeEntity(LoanCreateRequestDTO loanCreateRequestDTO);
    Loan mapLoanUpdateRequestDTOToEmployeeEntity(LoanUpdateRequestDTO loanUpdateRequestDTO);
    @Mappings({
            @Mapping(target = "customerId", source = "loan.customer.id")
    })
    LoanResponseDTO mapLoanEntityToLoanResponseDTO(Loan loan);
}
