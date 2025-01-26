package com.ensolvers.mynotepad.my_notepad.controller.request.note;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteUpdateRequest {

    @NotNull
    @Min(value = 1)
    @Max(value = 999999999999999999L)
    @Schema(description = "Note unique id", example = "-1")
    private Long id;

    @Size(min = 1, max = 80)
    @Schema(description = "Note title", example = "My first note")
    private String title;

    @Size(min = 1, max = 255)
    @Schema(description = "Content text of the note", example = "Hi! This is my first note, this is really exiting")
    private String text;

    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    @Schema(description = "Category unique id or 0 if you want to remove the category", example = "-1")
    private Integer categoryId;

    @Pattern(regexp = "^(ACTIVE|ARCHIVED)$", message = ": has to be ACTIVE or ARCHIVED")
    @Size(min = 1, max = 8)
    @Schema(description = "Note state", example = "ACTIVE")
    private String state;

}
