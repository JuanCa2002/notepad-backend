package com.ensolvers.mynotepad.my_notepad.exception.user;

import com.ensolvers.mynotepad.my_notepad.exception.general.BusinessException;

public class UserBusinessException extends BusinessException {

    public UserBusinessException(String message) {
        super(message);
    }
}
