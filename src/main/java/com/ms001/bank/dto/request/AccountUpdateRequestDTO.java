package com.ms001.bank.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequestDTO {
    @NotNull(message = "Check Activity field cannot be null")
    private boolean isActive;
//    private Long userId;
//    private List<Long> cardIds;

}
