package com.ms001.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequestDTO {
    private String firstName;
    private String lastName;
    private String fatherName;
//    private String password;
    private String phoneNumber;
}
