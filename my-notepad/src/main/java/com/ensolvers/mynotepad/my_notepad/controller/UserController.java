package com.ensolvers.mynotepad.my_notepad.controller;

import com.ensolvers.mynotepad.my_notepad.controller.api.UserApi;
import com.ensolvers.mynotepad.my_notepad.controller.mapper.UserRestMapper;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserAuthenticationRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserEmailRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserSetPasswordRequest;
import com.ensolvers.mynotepad.my_notepad.controller.request.user.UserUpdateRequest;
import com.ensolvers.mynotepad.my_notepad.controller.response.UserResponse;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import com.ensolvers.mynotepad.my_notepad.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.user}")
public class UserController implements UserApi {

    private final UserService service;

    private final UserRestMapper restMapper;

    @PostMapping
    @Override
    public ResponseEntity<UserResponse> save(UserRequest request) throws ApiException {
        var result = service.save(restMapper.requestToDto(request));
        return new ResponseEntity<>(restMapper.dtoToResponse(result), HttpStatus.CREATED);
    }

    @GetMapping("/email")
    @Override
    public ResponseEntity<UserResponse> getByEmail(UserEmailRequest request) throws ApiException {
        var result = service.findByEmail(request.getEmail());
        return new ResponseEntity<>(restMapper.dtoToResponse(result), HttpStatus.OK);
    }

    @GetMapping("/authentication")
    @Override
    public ResponseEntity<UserResponse> getByCredentials(UserAuthenticationRequest request) throws ApiException {
        var result = service.findByAuthentication(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(restMapper.dtoToResponse(result), HttpStatus.OK);
    }

    @PatchMapping("/set-password")
    @Override
    public ResponseEntity<Void> setPassword(UserSetPasswordRequest request) throws ApiException {
        service.setPassword(request.getId(), request.getCurrentPassword(), request.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @Override
    public ResponseEntity<UserResponse> update(UserUpdateRequest request) throws ApiException {
        var result = service.update(restMapper.requestToDto(request));
        return new ResponseEntity<>(restMapper.dtoToResponse(result), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<Void> updateState(@PathVariable BigInteger id) throws ApiException {
        service.updateState(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
