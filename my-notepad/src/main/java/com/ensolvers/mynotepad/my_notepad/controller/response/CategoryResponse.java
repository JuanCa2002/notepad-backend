package com.ensolvers.mynotepad.my_notepad.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class CategoryResponse {

    @Schema(description = "Category unique id", example = "-1")
    private Integer id;

    @Schema(description = "Category Name", example = "FAMILY")
    private String name;

    @Schema(description = "User unique id", example = "-1")
    private BigInteger userId;
}
