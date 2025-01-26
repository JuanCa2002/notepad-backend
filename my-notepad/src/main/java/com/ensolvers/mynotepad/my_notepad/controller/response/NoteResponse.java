package com.ensolvers.mynotepad.my_notepad.controller.response;

import com.ensolvers.mynotepad.my_notepad.dto.enums.NoteState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class NoteResponse {
    @Schema(description = "Note unique id", example = "-1")
    private Long id;

    @Schema(description = "Note title", example = "My first note")
    private String title;

    @Schema(description = "Content text of the note", example = "Hi! This is my first note, this is really exiting")
    private String text;

    @Schema(description = "Creation date of the note", example = "2002-08-22 00:00:00")
    private LocalDate creationDate;

    @Schema(description = "Creation time of the note", example = "15:22:00")
    private LocalTime creationTime;

    @Schema(description = "Note state", example = "ACTIVE")
    private NoteState state;

    @Schema(description = "User unique id", example = "-1")
    private BigInteger userId;

    @Schema(description = "Category Note if is assigned", example = "{ id: -1, name: 'FAMILY', userId: '-1' }")
    private CategoryResponse category;

}
