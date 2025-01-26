package com.ensolvers.mynotepad.my_notepad.controller.request.note;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
public class NoteListRequest {

    @NotNull
    @NotEmpty
    @Valid
    @Schema(description = "Ids list")
    private List<@Valid
            @NotNull
            @Min(value = 1)
            @Max(value = 999999999999999999L) Long> ids;

    @NotNull
    @Min(value = 1)
    @Schema(description = "User unique id", example = "-1")
    private BigInteger userId;
}
