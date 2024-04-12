package com.pblgllgs.backend.exception;
/*
 *
 * @author pblgl
 * Created on 11-04-2024
 *
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private Integer businessErrorCode;
    private String businessErrorDescription;
    private String error;
    private String path;
    private LocalDateTime localDateTime;
//    private Map<String,String> validationErrors;
    private Set<String> validationErrors;
    private Map<String,String> errors;
}
