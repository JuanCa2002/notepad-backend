package com.ensolvers.mynotepad.my_notepad.exception.user;

import com.ensolvers.mynotepad.my_notepad.exception.general.NotFoundException;

public class UserNotFoundByEmailException extends NotFoundException {
    public UserNotFoundByEmailException() {
        super(UserApiErrorMessages.USER_NOT_FOUND_BY_EMAIL);
    }
}
