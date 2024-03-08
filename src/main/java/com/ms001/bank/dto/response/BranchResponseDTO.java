package com.ms001.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponseDTO {
    private Long id;
    private String branchName;
    private String location;
    private String bankName;
}
