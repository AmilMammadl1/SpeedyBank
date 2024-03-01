package com.ms001.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private double salary;
    private Long departmentId;
}
