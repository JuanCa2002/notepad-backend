package com.ensolvers.mynotepad.my_notepad.controller.request.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequest{

    @NotNull
    @Min(value = 1)
    @Max(value = Integer.MAX_VALUE)
    @Schema(description = "Category unique id", example = "-1")
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Z ]+$", message = ": should only contain capital letters and spaces.")
    @Schema(description = "Category name", example = "FAMILY")
    private String name;

    @Size(min = 1, max = 50)
    @Schema(description = "Tag color to show in the application", example = "#f1a7b4")
    private String tagColor;

    @Size(min = 1, max = 50)
    @Schema(description = "Text color of the tag to show in the application", example = "white")
    private String textColor;
}
