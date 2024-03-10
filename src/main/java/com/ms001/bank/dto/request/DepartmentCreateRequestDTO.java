package com.ms001.bank.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class DepartmentCreateRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Bank name cannot be blank")
    @Size(max = 255, message = "Bank name cannot exceed 255 characters")
    private String bankName;

//    private List<Long> employeeIds;

}
