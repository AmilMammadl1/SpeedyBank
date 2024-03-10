package com.ms001.bank.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateRequestDTO {
    @Size(max = 255, message = "Firstname cannot exceed 255 characters")
    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;
    @Size(max = 255, message = "Lastname cannot exceed 255 characters")
    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;
    @Size(max = 255, message = "Fathername cannot exceed 255 characters")
    @NotBlank(message = "Fathername cannot be blank")
    private String fatherName;
    @Email(message = "Email provide a valid email address")
    private String email;
    @NotNull(message = "Salary cannot be null")
    private double salary;
    @NotNull(message = "Department cannot be null")
    private Long departmentId;
}
