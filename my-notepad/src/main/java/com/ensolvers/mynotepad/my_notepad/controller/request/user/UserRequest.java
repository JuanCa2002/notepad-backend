package com.ensolvers.mynotepad.my_notepad.controller.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "User first name", example = "JUAN")
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "User second name", example = "CAMILO")
    private String secondName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    @Schema(description = "User identification number", example = "100000241")
    private String identificationNumber;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "User first last name", example = "TORRES")
    private String firstLastName;

    @NotBlank
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "User second last name", example = "BELTRAN")
    private String secondLastName;

    @NotNull
    @NotBlank
    @Email
    @Size(min = 1, max = 50)
    @Schema(description = "User email", example = "example@email.com")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^\\S+$", message = ": should must not contain any spaces")
    @Schema(description = "User private password", example = "password")
    private String password;

    @NotNull
    @Min(value = 12)
    @Max(value = 100)
    @Schema(description = "User age", example = "18")
    private Integer age;
}
