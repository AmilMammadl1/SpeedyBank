package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.dto.request.TransactionCreateRequestDTO;
import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.dto.response.TransactionResponseDTO;
import com.ms001.bank.entity.Loan;
import com.ms001.bank.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    Transaction mapTransactionCreateRequestDTOToTransactionEntity(TransactionCreateRequestDTO transactionCreateRequestDTO);
    TransactionResponseDTO mapTransactionEntityToTransactionResponseDTO(Transaction transaction);
}
