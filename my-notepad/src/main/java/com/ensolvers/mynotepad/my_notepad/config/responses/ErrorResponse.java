package com.ensolvers.mynotepad.my_notepad.config.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @Schema(description = "Error message")
    private String message;

    @Schema(description = "Error HTTP code")
    private Integer code;
}
