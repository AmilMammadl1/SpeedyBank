package com.ms001.bank.dto.request;

import java.util.List;

public class BankRequestDTO {
    private String name;
    private List<Long> branchIds;  // Assuming you want to include branch IDs in the request DTO
    private List<Long> atmIds;  // Assuming you want to include ATM IDs in the request DTO
    private List<Long> employeeIds;  // Assuming you want to include employee IDs in the request DTO
    private List<Long> userIds;
}
