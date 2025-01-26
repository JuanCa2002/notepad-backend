package com.ensolvers.mynotepad.my_notepad.controller.api;

import com.ensolvers.mynotepad.my_notepad.controller.request.category.CategoryRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.category.CategoryUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserIdRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.CategoryResponse;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.util.List;

@Tag(name = "Category", description = "Category Services")
public interface CategoryApi {

    @Operation(summary = "Create new category", description = "Create a new register of a category")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Category created successfully", content = @Content(schema = @Schema(implementation = CategoryResponse.class)))})
    ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) throws ApiException;

    @Operation(summary = "Update category", description = "Update an existing category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Category updated successfully", content = @Content(schema = @Schema(implementation = CategoryResponse.class)))})
    ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest request) throws ApiException;

    @Operation(summary = "Remove category", description = "Delete an existing category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Category deleted successfully")})
    ResponseEntity<Void> delete(@Valid
                                @NotNull
                                @Min(value = 1)
                                @Max(value = Integer.MAX_VALUE)
                                @Schema(description = "Category unique id", example = "-1")
                                Integer id,
                                @Valid @ParameterObject UserIdRequest request) throws ApiException;

    @Operation(summary = "Find all categories by user", description = "Find all created categories by user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Category List by selected user", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class)))})})
    ResponseEntity<List<CategoryResponse>> getAllByUser(
            @Valid
            @NotNull
            @Min(value = 1)
            @Schema(description = "User unique id", example = "-1")
            BigInteger userId);
}
