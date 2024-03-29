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
public class CustomerUpdateRequestDTO {
    @NotBlank(message = "Firstname cannot be blank")
    @Size(max = 255, message = "Firstname cannot exceed 255 characters")
    private String firstName;
    @NotBlank(message = "Lastname cannot be blank")
    @Size(max = 255, message = "Lastname cannot exceed 255 characters")
    private String lastName;
    @NotBlank(message = "Fathername cannot be blank")
    @Size(max = 255, message = "Fathername cannot exceed 255 characters")
    private String fatherName;

    //    private String password;
    @NotBlank(message = "phone number cannot be blank")
    @Size(max = 255, message = "phone number cannot exceed 255 characters")
    private String phoneNumber;
}
