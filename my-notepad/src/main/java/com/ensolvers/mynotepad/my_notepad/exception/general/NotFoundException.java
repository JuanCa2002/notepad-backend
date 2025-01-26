package com.ensolvers.mynotepad.my_notepad.exception.general;

public class NotFoundException extends ApiException{

    public NotFoundException(String message){
        super(message, 404);
    }
}
