package com.ensolvers.mynotepad.my_notepad.dto;

import com.ensolvers.mynotepad.my_notepad.dto.enums.UserState;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class UserDto {

    private BigInteger id;

    private String name;

    private String secondName;

    private String identificationNumber;

    private String firstLastName;

    private String secondLastName;

    private String email;

    private String password;

    private Integer age;

    private UserState state;
}
