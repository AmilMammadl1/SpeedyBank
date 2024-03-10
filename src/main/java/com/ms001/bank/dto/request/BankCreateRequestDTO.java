package com.ms001.bank.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankCreateRequestDTO {
    @NotBlank(message = "Bank name cannot be blank")
    @Size(max = 255, message = "Bank name cannot exceed 255 characters")
    private String name;
}
