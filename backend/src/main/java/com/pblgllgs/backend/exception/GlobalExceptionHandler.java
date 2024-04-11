package com.pblgllgs.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDTO.setTimestamp(new Date());
        errorDTO.setPath(((ServletWebRequest) request).getRequest().getServletPath());
        errorDTO.setErrors(extractErrors(ex.getBindingResult().getFieldErrors()));
        log.info(extractErrors(ex.getBindingResult().getFieldErrors()).toString());
        return new ResponseEntity<>(errorDTO, headers, status);
    }

    private Map<String, String> extractErrors(List<FieldError> listErrors) {
        Map<String, String> errorsMap = new HashMap<>();
        listErrors.forEach(error -> errorsMap.put(error.getField(), error.getDefaultMessage()));
        return errorsMap;
    }

}
