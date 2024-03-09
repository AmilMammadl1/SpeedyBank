package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.AccountUpdateRequestDTO;
import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.response.AccountResponseDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.entity.Account;
import com.ms001.bank.entity.Branch;
import com.ms001.bank.entity.Card;
import com.ms001.bank.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    Account mapAccountUpdateRequestDTOToAccountEntity(AccountUpdateRequestDTO updateRequestDTO);
    @Mappings({
            @Mapping(target = "customerId", source = "account.customer.id"),
            @Mapping(target = "cardIds", source = "account.cards")
    })
    AccountResponseDTO mapAccountEntityToAccountResponseDTO(Account account);
    default List<Long> mapCardListToLongList(List<Card> cards) {
        return cards.stream()
                .map(Card::getId)
                .collect(Collectors.toList());
    }
}