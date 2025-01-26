package com.ensolvers.mynotepad.my_notepad.controller.request.note;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteListUpdateStateRequest extends NoteListRequest{

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(ACTIVE|ARCHIVED)$", message = ": has to be ACTIVE or ARCHIVED")
    @Size(min = 1, max = 8)
    @Schema(description = "Note state", example = "ACTIVE")
    private String state;
}
