package com.ensolvers.mynotepad.my_notepad.controller.response;

import com.ensolvers.mynotepad.my_notepad.dto.enums.UserState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class UserResponse {

    @Schema(description = "User unique id", example = "-1")
    private BigInteger id;

    @Schema(description = "User first name", example = "JUAN")
    private String name;

    @Schema(description = "User second name", example = "CAMILO")
    private String secondName;

    @Schema(description = "User identification number", example = "5548444551")
    private String identificationNumber;

    @Schema(description = "User first last name", example = "TORRES")
    private String firstLastName;

    @Schema(description = "User second last name", example = "BELTRAN")
    private String secondLastName;

    @Schema(description = "User email", example = "example@email.com")
    private String email;

    @Schema(description = "User age", example = "18")
    private Integer age;

    @Schema(description = "User state", example = "ACTIVE")
    private UserState state;
}
