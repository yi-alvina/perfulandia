package com.mcontreras.msvc.usuario.exceptions;

import com.mcontreras.msvc.usuario.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorDTO createErrorDTO(int status, Date date, Map<String, String> errorsMap){
        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setStatus(status);
        errorDTO.setDate(date);
        errorDTO.setErrors(errorsMap);

        return errorDTO;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> errorsMap = new HashMap<>();
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(this.createErrorDTO(HttpStatus.BAD_REQUEST.value(), new Date(), errorsMap));
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<ErrorDTO> handleUsuarioException(UsuarioException exception){

        if (exception.getMessage().contains("Usuario no encontrado en la base de datos")){
            Map<String, String> errorsMap = Collections.singletonMap("Usuario no encontrado en la base de datos", exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(this.createErrorDTO(HttpStatus.NOT_FOUND.value(), new Date(), errorsMap));
        }else{
            Map<String, String> errorsMap = Collections.singletonMap("Usuario existente", exception.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(this.createErrorDTO(HttpStatus.CONFLICT.value(), new Date(), errorsMap));
        }
    }
}
