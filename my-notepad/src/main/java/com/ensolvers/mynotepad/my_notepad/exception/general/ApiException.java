package com.ensolvers.mynotepad.my_notepad.exception.general;

import lombok.Getter;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@Getter
public class ApiException extends Exception{

    private String message;
    private final String routeMessage;
    private final Integer code;
    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    private static final Locale LOCALE_DEFAULT = Locale.ENGLISH;

    public ApiException(String message, Integer code) {
        super(message);
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        this.message = getMessage(message, null);
        this.code = code;
        this.routeMessage = message;
    }

    public void addParams(Object params){
        this.message = getMessage(routeMessage,new Object[]{params});
    }

    public String getMessage(String errorRoute, Object[] params){
        return messageSource.getMessage(errorRoute, params,LOCALE_DEFAULT);
    }

}
