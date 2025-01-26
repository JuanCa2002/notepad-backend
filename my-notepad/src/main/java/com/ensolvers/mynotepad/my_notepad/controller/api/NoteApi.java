package com.ensolvers.mynotepad.my_notepad.controller.api;

import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteFilterRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteListRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteListUpdateCategoryRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteListUpdateStateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.note.NoteUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.NoteResponse;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Note", description = "Note Services")
public interface NoteApi {

    @Operation(summary = "Create new note", description = "Create a new register of a note")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Note created successfully", content = @Content(schema = @Schema(implementation = NoteResponse.class)))})
    ResponseEntity<NoteResponse> save(@Valid @RequestBody NoteRequest request) throws ApiException;

    @Operation(summary = "Update note", description = "Update an existing register of a note")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Note updated successfully", content = @Content(schema = @Schema(implementation = NoteResponse.class)))})
    ResponseEntity<NoteResponse> update(@Valid @RequestBody NoteUpdateRequest request) throws ApiException;

    @Operation(summary = "Delete a list of notes", description = "Delete note list")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Notes deleted successfully")})
    ResponseEntity<Void> delete(@Valid @ParameterObject NoteListRequest request);

    @Operation(summary = "Update state of a list of notes", description = "Update state from a list of notes")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Notes state updated successfully", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NoteResponse.class)))})})
    ResponseEntity<List<NoteResponse>> updateState(@Valid @ParameterObject NoteListUpdateStateRequest request);

    @Operation(summary = "Update category of a list of notes", description = "Update category from a list of notes")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Notes category updated successfully", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NoteResponse.class)))})})
    ResponseEntity<List<NoteResponse>> updateCategory(@Valid @ParameterObject NoteListUpdateCategoryRequest request) throws ApiException;

    @Operation(summary = "Get all notes by user", description = "Get all notes by user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Note list by selected user", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NoteResponse.class)))})})
    ResponseEntity<List<NoteResponse>> getAllByUser(@Valid @ParameterObject NoteFilterRequest request) throws ApiException;
}
