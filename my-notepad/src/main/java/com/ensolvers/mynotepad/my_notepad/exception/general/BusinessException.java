package com.ensolvers.mynotepad.my_notepad.exception.general;

public class BusinessException extends ApiException{
    public BusinessException(String message){
        super(message, 400);
    }
}
