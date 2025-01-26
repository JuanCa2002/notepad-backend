package com.ensolvers.mynotepad.my_notepad.service;

import com.ensolvers.mynotepad.my_notepad.dto.UserDto;
import com.ensolvers.mynotepad.my_notepad.dto.enums.UserState;
import com.ensolvers.mynotepad.my_notepad.entity.UserEntity;
import com.ensolvers.mynotepad.my_notepad.exception.general.ApiException;
import com.ensolvers.mynotepad.my_notepad.exception.user.UserApiErrorMessages;
import com.ensolvers.mynotepad.my_notepad.exception.user.UserBusinessException;
import com.ensolvers.mynotepad.my_notepad.exception.user.UserNotFoundByEmailException;
import com.ensolvers.mynotepad.my_notepad.exception.user.UserNotFoundException;
import com.ensolvers.mynotepad.my_notepad.mapper.UserMapper;
import com.ensolvers.mynotepad.my_notepad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    private final UserMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public UserDto save(UserDto userDto) throws ApiException {
        // Validate if a user already exists by email or identification number
        boolean existsByEmail = repository.existsByEmail(userDto.getEmail());
        boolean existsByIdentification = repository.existsByIdentificationNumber(userDto.getIdentificationNumber());

        if(existsByEmail || existsByIdentification){
           UserBusinessException error = new UserBusinessException(existsByEmail ? UserApiErrorMessages.USER_BY_EMAIL_ALREADY_EXISTS: UserApiErrorMessages.USER_BY_IDENTIFICATION_NUMBER_ALREADY_EXISTS);
           error.addParams(existsByEmail ? userDto.getEmail(): userDto.getIdentificationNumber());
           throw error;
        }

        // Map Dto to Entity
        UserEntity entity = mapper.dtoToEntity(userDto);

        // Encoding given password and set state to ACTIVE
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(entity.getPassword());
        entity.setPassword(hashedPassword);
        entity.setState(UserState.ACTIVE);

        // Save user in the database
        return mapper.entityToDto(repository.save(entity));
    }

    @Override
    public UserDto findById(BigInteger id) throws ApiException {
        // Validate if a user exists by the given ID
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addParams(id);
        UserEntity foundUser = repository.findById(id).orElseThrow(() -> errorNotFound);

        // Return the found user and map it into Dto
        return mapper.entityToDto(foundUser);
    }

    @Transactional(readOnly = true, rollbackFor = ApiException.class)
    @Override
    public UserDto findByAuthentication(String email, String password) throws ApiException {
        UserBusinessException error = new UserBusinessException(UserApiErrorMessages.USER_PASSWORD_EMAIL_NOT_MATCHES);
        try{
            // Finding user by email
            UserDto foundUser = findByEmail(email);

            // Compare the entered password against the saved one
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean passwordMatches = encoder.matches(password,  foundUser.getPassword());

            // If the passwords don't match throw error
            if(!passwordMatches) throw error;

            // If the passwords match return found user
            return foundUser;
        }catch (UserNotFoundByEmailException e){
            // If catch exception not found by email throw error
            throw error;
        }
    }

    @Transactional(readOnly = true, rollbackFor = ApiException.class)
    @Override
    public UserDto findByEmail(String email) throws ApiException {
        // Validate if a user exists by this email
        UserNotFoundByEmailException errorNotFound = new UserNotFoundByEmailException();
        UserEntity entity = repository.findByEmail(email).orElseThrow(() -> errorNotFound);

        // Map entity to dto
        return mapper.entityToDto(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public void setPassword(BigInteger userId, String currentPassword, String newPassword) throws ApiException {
        UserBusinessException error = new UserBusinessException(UserApiErrorMessages.USER_PASSWORDS_NOT_MATCH);
        UserDto foundUser = findById(userId);

        // Compare the entered password against the saved one
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordMatches = encoder.matches(currentPassword, foundUser.getPassword());

        // If the passwords don't match throw error
        if(!passwordMatches) throw error;

        // Encoding and setting the new password
        String hashedPassword = encoder.encode(newPassword);
        foundUser.setPassword(hashedPassword);

        // Save user with new password
        repository.save(mapper.dtoToEntity(foundUser));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public UserDto update(UserDto userDto) throws ApiException {
        // Finding user to update
        UserDto foundUser = findById(userDto.getId());

        // Validate if a user already exists by email or identification number
        boolean existsByEmail = repository.existsByEmail(userDto.getEmail());
        boolean existsByIdentification = repository.existsByIdentificationNumber(userDto.getIdentificationNumber());

        if(existsByEmail || existsByIdentification){
            UserBusinessException error = new UserBusinessException(existsByEmail ? UserApiErrorMessages.USER_BY_EMAIL_ALREADY_EXISTS: UserApiErrorMessages.USER_BY_IDENTIFICATION_NUMBER_ALREADY_EXISTS);
            error.addParams(existsByEmail ? userDto.getEmail(): userDto.getIdentificationNumber());
            throw error;
        }

        // Merge not null values (New ones)
        mapper.mergeToUpdate(foundUser, userDto);

        // Update user with new information
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(foundUser)));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ApiException.class)
    @Override
    public void updateState(BigInteger id) throws ApiException {
        // Finding user to update state
        UserDto foundUser = findById(id);

        // Set state to the new one
        foundUser.setState(foundUser.getState().equals(UserState.ACTIVE) ? UserState.INACTIVE : UserState.ACTIVE);

        // Update user with new state
        repository.save(mapper.dtoToEntity(foundUser));
    }
}
