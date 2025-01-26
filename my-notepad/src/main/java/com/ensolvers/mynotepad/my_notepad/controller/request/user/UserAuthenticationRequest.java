package com.ensolvers.mynotepad.my_notepad.controller.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationRequest extends UserEmailRequest{

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^\\S+$", message = ": should must not contain any spaces")
    @Schema(description = "User private password", example = "password")
    private String password;
}
