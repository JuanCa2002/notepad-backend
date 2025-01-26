package com.ensolvers.mynotepad.my_notepad.controller.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UserSetPasswordRequest {

    @NotNull
    @Min(value = 1)
    @Schema(description = "User unique id", example = "-1")
    private BigInteger id;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^\\S+$", message = ": should must not contain any spaces")
    @Schema(description = "User private current password", example = "password")
    private String currentPassword;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^\\S+$", message = ": should must not contain any spaces")
    @Schema(description = "User private new password", example = "password")
    private String newPassword;
}
