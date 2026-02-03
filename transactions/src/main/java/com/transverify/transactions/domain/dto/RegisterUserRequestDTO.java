package com.transverify.transactions.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterUserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String IBAN;
}
