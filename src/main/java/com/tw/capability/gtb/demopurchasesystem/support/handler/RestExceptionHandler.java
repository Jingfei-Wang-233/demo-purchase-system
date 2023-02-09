package com.tw.capability.gtb.demopurchasesystem.support.handler;

import com.tw.capability.gtb.demopurchasesystem.support.exception.ApiError;
import com.tw.capability.gtb.demopurchasesystem.support.exception.UserDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handle(MethodArgumentNotValidException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(";"));
        return new ApiError(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                message
        );
    }

    @ExceptionHandler(value = UserDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handle(UserDuplicateException ex) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        return new ApiError(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                ex.getMessage()
        );
    }
}
