package br.com.santander.digital.formalization.adapters.in.controllers;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.santander.digital.formalization.application.core.exception.BusinessException;

import javax.naming.AuthenticationException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void handleException(Exception e) {
        log.error("Error: ", ExceptionUtils.getStackTrace(e));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public void notFoundException(
            final NotFoundException ex) {
        log.error(ex.getMessage(), ExceptionUtils.getStackTrace(ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public String handleIllegalArgumentException(
            IllegalArgumentException ex) {
        log.error(ex.getMessage(), ExceptionUtils.getStackTrace(ex));
        return ex.getMessage();
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({BusinessException.class})
    public String handle(
            IllegalArgumentException ex) {
        log.error(ex.getMessage(), ExceptionUtils.getStackTrace(ex));
        return ex.getMessage();
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAuthenticationException(
            AuthenticationException ex) {
        log.error(ex.getMessage(), ExceptionUtils.getStackTrace(ex));
        return ex.getMessage();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleIllegalArgumentException(
            MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ExceptionUtils.getStackTrace(ex));
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(
                        Collectors.toMap(
                                FieldError::getField,
                                fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse("Invalid value")
                        ));
    }

}
