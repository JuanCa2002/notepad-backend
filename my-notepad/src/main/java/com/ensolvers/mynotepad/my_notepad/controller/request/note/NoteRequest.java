package com.ensolvers.mynotepad.my_notepad.controller.request.note;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class NoteRequest {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 80)
    @Schema(description = "Note title", example = "My first note")
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    @Schema(description = "Content text of the note", example = "Hi! This is my first note, this is really exiting")
    private String text;

    @NotNull
    @Min(value = 1)
    @Schema(description = "User unique id", example = "-1")
    private BigInteger userId;

    @Min(value = 1)
    @Max(value = Integer.MAX_VALUE)
    @Schema(description = "Category unique id", example = "-1")
    private Integer categoryId;
}
