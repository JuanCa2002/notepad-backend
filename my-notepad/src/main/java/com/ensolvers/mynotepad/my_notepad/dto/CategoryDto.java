package com.ensolvers.mynotepad.my_notepad.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private Integer id;

    private String name;

    private String tagColor;

    private String textColor;

    private UserDto user;

    private List<NoteDto> notes;
}
