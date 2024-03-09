package com.ms001.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateRequestDTO {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private double salary;
    private Long departmentId;
}
