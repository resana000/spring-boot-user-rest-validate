package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserCriteriaDto {

    String name;

    String lastName;

    String surname;

    String phoneNumber;

    String email;
}
