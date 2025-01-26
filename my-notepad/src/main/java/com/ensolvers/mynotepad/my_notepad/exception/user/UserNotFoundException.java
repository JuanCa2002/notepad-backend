package com.ensolvers.mynotepad.my_notepad.exception.user;

import com.ensolvers.mynotepad.my_notepad.exception.general.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(UserApiErrorMessages.USER_NOT_FOUND);
    }
}
