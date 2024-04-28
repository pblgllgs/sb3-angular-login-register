package com.pblgllgs.backend.exception;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            LockedException ex,
            HttpServletRequest request
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.ACCOUNT_LOCKED.getCode())
                .businessErrorDescription(BusinessErrorCodes.ACCOUNT_LOCKED.getDescription())
                .error(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            DisabledException ex,
            HttpServletRequest request
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.ACCOUNT_DISABLED.getCode())
                .businessErrorDescription(BusinessErrorCodes.ACCOUNT_DISABLED.getDescription())
                .error(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            BadCredentialsException ex,
            HttpServletRequest request
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.BAD_CREDENTIALS.getCode())
                .businessErrorDescription(BusinessErrorCodes.BAD_CREDENTIALS.getDescription())
                .error(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            MessagingException ex,
            HttpServletRequest request
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .error(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Set<String> errors = new HashSet<>();
        ex.getBindingResult().getAllErrors()
                .forEach(
                        error -> {
                            String errorMessage = error.getDefaultMessage();
                            errors.add(errorMessage);
                        }
                );
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .validationErrors(errors)
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionResponse> handleException(
//            MethodArgumentNotValidException ex,
//            HttpServletRequest request
//    ) {
//        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
//                .validationErrors(extractErrors(ex.getBindingResult().getFieldErrors()))
//                .localDateTime(LocalDateTime.now())
//                .path(request.getRequestURI())
//                .build();
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    private Map<String, String> extractErrors(List<FieldError> listErrors) {
//        Map<String, String> errorsMap = new HashMap<>();
//        listErrors.forEach(error -> errorsMap.put(error.getField(), error.getDefaultMessage()));
//        return errorsMap;
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleException(
//            Exception ex,
//            HttpServletRequest request
//    ) {
//        ex.printStackTrace();
//        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
//                .businessErrorDescription("Internal error, contact admin")
//                .error(ex.getMessage())
//                .localDateTime(LocalDateTime.now())
//                .path(request.getRequestURI())
//                .build();
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.RESOURCE_NOT_FOUND.getCode())
                .businessErrorDescription(BusinessErrorCodes.RESOURCE_NOT_FOUND.getDescription())
                .error(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            OperationNotPermittedException ex,
            HttpServletRequest request
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.OPERATION_NOT_PERMITTED.getCode())
                .businessErrorDescription(BusinessErrorCodes.OPERATION_NOT_PERMITTED.getDescription())
                .error(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ActivationCodeExpiredException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            ActivationCodeExpiredException ex,
            HttpServletRequest request
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.ACTIVATION_CODE_EXPIRED.getCode())
                .businessErrorDescription(BusinessErrorCodes.ACTIVATION_CODE_EXPIRED.getDescription())
                .error(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleException(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.RESOURCE_ALREADY_EXISTS.getCode())
                .businessErrorDescription(BusinessErrorCodes.RESOURCE_ALREADY_EXISTS.getDescription())
                .error(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
