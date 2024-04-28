package com.pblgllgs.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {

    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Current password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "The new password does not match"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "User account is locked"),
    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "User account is disabled"),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN, "Invalid credentials"),
    RESOURCE_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Resource not found"),
    ACTIVATION_CODE_EXPIRED(400, HttpStatus.BAD_REQUEST, "Activation code expired"),
    RESOURCE_ALREADY_EXISTS(400, HttpStatus.BAD_REQUEST, "Resource already exists"),
    OPERATION_NOT_PERMITTED(400, HttpStatus.BAD_REQUEST, "Operation not permitted"),
    ;

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }
}
