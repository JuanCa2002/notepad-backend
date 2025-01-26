package com.ensolvers.mynotepad.my_notepad.controller.api;

import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserAuthenticationRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserEmailRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserSetPasswordRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.UserResponse;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;

@Tag(name = "User", description = "User Services")
public interface UserApi {

    @Operation(summary = "Create new user", description = "Create a new register of a user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest request) throws ApiException;

    @Operation(summary = "Find user by email", description = "Find user by email")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found user by email", content = @Content(schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> getByEmail(@Valid @ParameterObject UserEmailRequest request) throws ApiException;

    @Operation(summary = "Find user by credentials", description = "Find user by email and password")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found user by authentication credentials", content = @Content(schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> getByCredentials(@Valid @ParameterObject UserAuthenticationRequest request) throws ApiException;

    @Operation(summary = "Set password", description = "Set password to a new one")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Password set successfully")})
    ResponseEntity<Void> setPassword(@Valid @ParameterObject UserSetPasswordRequest request) throws ApiException;

    @Operation(summary = "Update user", description = "Update user information")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> update(@Valid @RequestBody UserUpdateRequest request) throws ApiException;

    @Operation(summary = "Update user state", description = "Update user state")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User state updated successfully")})
    ResponseEntity<Void> updateState(@Valid
                                     @NotNull
                                     @Min(value = 1)
                                     @Schema(description = "User unique id", example = "-1")
                                     BigInteger id) throws ApiException;


}
