package com.pblgllgs.backend.exception;
/*
 *
 * @author pblgl
 * Created on 18-02-2024
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class OperationNotPermittedException extends RuntimeException{

    public OperationNotPermittedException(String message) {
        super(message);
    }
}
