package com.pblgllgs.backend.exception;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorDTO {
    private Date timestamp;
    private int status;
    private String path;
    private Map<String,String> errors = new HashMap<>();
}
