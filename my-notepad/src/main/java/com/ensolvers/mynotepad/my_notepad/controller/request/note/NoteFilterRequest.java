package com.ensolvers.mynotepad.my_notepad.controller.request.note;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class NoteFilterRequest {

    @Valid
    @NotNull
    @Min(value = 1)
    @Schema(description = "User unique id", example = "-1")
    private BigInteger userId;

    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    @Schema(description = "Category unique id", example = "-1")
    private Integer categoryId;
}
