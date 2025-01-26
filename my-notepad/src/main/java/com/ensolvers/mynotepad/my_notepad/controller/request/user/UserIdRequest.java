package com.ensolvers.mynotepad.my_notepad.controller.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class UserIdRequest {

    @NotNull
    @Min(value = 1)
    @Schema(description = "User unique id", example = "-1")
    private BigInteger userId;
}
