package com.ms001.bank.dto.request;

import com.ms001.bank.constant.SupportedServicesType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ATMCreateRequestDTO {
    @NotBlank(message = "Location name cannot be blank")
    @Size(max = 255, message = "Location name cannot exceed 255 characters")
    private String location;
    @NotBlank(message = "Bank name cannot be blank")
    @Size(max = 255, message = "Bank name cannot exceed 255 characters")
    private String bankName;
}
