package com.ensolvers.mynotepad.my_notepad.controller.request.note;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteListUpdateCategoryRequest extends NoteListRequest{

    @NotNull
    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    @Schema(description = "Category unique id or 0 if you want to remove the category", example = "-1")
    private Integer categoryId;
}
