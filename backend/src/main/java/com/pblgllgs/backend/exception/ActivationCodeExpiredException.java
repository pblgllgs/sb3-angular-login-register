package com.pblgllgs.backend.exception;
/*
 *
 * @author pblgl
 * Created on 18-02-2024
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ActivationCodeExpiredException extends RuntimeException{

    public ActivationCodeExpiredException(String message) {
        super(message);
    }
}
