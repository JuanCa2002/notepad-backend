package com.ensolvers.mynotepad.my_notepad.controller.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEmailRequest {

    @NotNull
    @NotBlank
    @Email
    @Size(min = 1, max = 50)
    @Schema(description = "User email", example = "example@email.com")
    private String email;
}
