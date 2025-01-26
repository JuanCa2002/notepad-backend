package com.ensolvers.mynotepad.my_notepad.service;

import com.ensolvers.mynotepad.my_notepad.dto.UserDto;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;

import java.math.BigInteger;

public interface UserService {

    UserDto save(UserDto userDto) throws ApiException;

    UserDto findById(BigInteger id) throws ApiException;

    UserDto findByAuthentication(String email, String password) throws ApiException;

    UserDto findByEmail(String email) throws ApiException;

    void setPassword(BigInteger userId, String currentPassword, String newPassword) throws ApiException;

    UserDto update(UserDto userDto) throws ApiException;

    void updateState(BigInteger id) throws ApiException;

}
