package com.ms001.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankResponseDTO {
    private String name;
    private List<Long> branchIds;  // Assuming you want to include branch IDs in the DTO
    private List<Long> atmIds;  // Assuming you want to include ATM IDs in the DTO
    private List<Long> departmentIds;  // Assuming you want to include employee IDs in the DTO
    private List<Long> customerIds;
}
