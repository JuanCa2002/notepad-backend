package com.ensolvers.mynotepad.my_notepad.controller.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class UserUpdateRequest {

    @NotNull
    @Min(value = 1)
    @Schema(description = "User unique id", example = "-1")
    private BigInteger id;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "User first name", example = "JUAN")
    private String name;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "User second name", example = "CAMILO")
    private String secondName;

    @Size(min = 1, max = 20)
    @Schema(description = "User identification number", example = "100000241")
    private String identificationNumber;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "User first last name", example = "TORRES")
    private String firstLastName;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "User second last name", example = "BELTRAN")
    private String secondLastName;

    @Email
    @Size(min = 1, max = 50)
    @Schema(description = "User email", example = "example@email.com")
    private String email;
}
