package com.ensolvers.mynotepad.my_notepad.dto;

import com.ensolvers.mynotepad.my_notepad.dto.enums.NoteState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class NoteDto {

    private Long id;

    private String title;

    private String text;

    private LocalDate creationDate;

    private LocalTime creationTime;

    private NoteState state;

    private UserDto user;

    private CategoryDto category;
}
