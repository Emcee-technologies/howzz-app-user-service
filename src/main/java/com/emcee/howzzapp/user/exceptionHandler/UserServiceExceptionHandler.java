package com.emcee.howzzapp.user.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.emcee.howzzapp.user.exception.OtpException;
import com.emcee.howzzapp.user.exception.UserRegException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * UserServiceExceptionHandler
 */
@Slf4j
@ControllerAdvice
public class UserServiceExceptionHandler {

    /**
     * Handles UserRegException
     * 
     * @param ex
     * @return ResponseEntity with error message and HTTP status
     *         BAD_REQUEST
     */
    @ExceptionHandler(UserRegException.class)
    public ResponseEntity<?> handleUserRegException(UserRegException ex) {
        log.error("UserRegException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles OtpException
     * 
     * @param ex
     * @return ResponseEntity with error message and HTTP status
     *         BAD_REQUEST
     */
    @ExceptionHandler(OtpException.class)
    public ResponseEntity<?> handleOtpException(OtpException ex) {
        log.error("OtpException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
