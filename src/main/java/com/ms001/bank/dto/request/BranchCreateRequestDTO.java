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
public class BranchCreateRequestDTO {
    @NotBlank(message = "Branch name cannot be blank")
    @Size(max = 255, message = "Branch name cannot exceed 255 characters")
    private String branchName;
    @NotBlank(message = "Location cannot be blank")
    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private String location;
    @NotBlank(message = "Bank name cannot be blank")
    @Size(max = 255, message = "Bank name cannot exceed 255 characters")
    private String bankName;
}
