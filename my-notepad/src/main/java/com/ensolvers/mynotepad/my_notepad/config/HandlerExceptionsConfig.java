package com.ensolvers.mynotepad.my_notepad.config;

import com.ensolvers.mynotepad.my_notepad.config.responses.ErrorResponse;
import com.ensolvers.mynotepad.my_notepad.exception.general.BusinessException;
import com.ensolvers.mynotepad.my_notepad.exception.general.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestControllerAdvice
public class HandlerExceptionsConfig {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBusinessException(BusinessException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleInternalServerError(Exception ex) {
        return new ErrorResponse("An unexpected error occurred. See the console for more details.", 500);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse response = new ErrorResponse();

        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
            String fieldName = fieldError.getField();
            String constraintType = fieldError.getCode();
            String error = "The field ";
            switch (Objects.requireNonNull(constraintType)) {
                case "NotNull":
                    error += fieldName+" can not be null";
                    break;
                case "NotEmpty":
                    error += fieldName+" can not be empty";
                    break;
                case "NotBlank":
                    error += fieldName+" can not be blank";
                    break;
                case "Min":
                    Object[] arguments = fieldError.getArguments();
                    if (arguments!= null && arguments.length >= 1) {
                        long minValue = (Long) arguments[1];
                        error += fieldName + " must be at least " + minValue;
                    }
                    break;
                case "Max":
                    Object[] maxArguments = fieldError.getArguments();
                    if (maxArguments!= null && maxArguments.length >= 2) {
                        long maxValue = (Long) maxArguments[1];
                        error += fieldName + " must not exceed " + maxValue;
                    }
                    break;
                case "Pattern":
                    error += fieldName + fieldError.getDefaultMessage();
                    break;
                case "Size":
                    Object[] sizeArguments = fieldError.getArguments();
                    if (sizeArguments!= null && sizeArguments.length >= 3) {
                        int minSize = (Integer) sizeArguments[2];
                        int maxSize = (Integer) sizeArguments[1];
                        error += fieldName + " must be between " + minSize + " and " + maxSize + " characters long";
                    }
                    break;
                default:
                    error += fieldName + " has an invalid value";
                    break;
            }
            response.setMessage(error);
            response.setCode(ex.getBody().getStatus());
        }
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse response = new ErrorResponse();

        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        String errorMessage = "Validation error(s): " +
                violations.stream()
                        .map(violation -> {
                            String field = violation.getPropertyPath().toString();
                            String message = violation.getMessage();
                            return "Field '" + field + "' " + message;
                        })
                        .collect(Collectors.joining(", "));

        response.setMessage(errorMessage);
        response.setCode(HttpStatus.BAD_REQUEST.value());

        return response;
    }

}
