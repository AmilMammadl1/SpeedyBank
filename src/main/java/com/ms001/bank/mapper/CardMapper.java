package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.CardCreateRequestDTO;
import com.ms001.bank.dto.request.CardUpdateRequestDTO;
import com.ms001.bank.dto.request.LoanCreateRequestDTO;
import com.ms001.bank.dto.request.LoanUpdateRequestDTO;
import com.ms001.bank.dto.response.CardResponseDTO;
import com.ms001.bank.dto.response.LoanResponseDTO;
import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    Card mapCardCreateRequestDTOToCardEntity(CardCreateRequestDTO cardCreateRequestDTO);
    Card mapCardUpdateRequestDTOToCardEntity(CardUpdateRequestDTO cardUpdateRequestDTO);
    @Mappings({
            @Mapping(target = "accountId", source = "card.account.id")
    })
    CardResponseDTO mapCardEntityToCardResponseDTO(Card card);
}