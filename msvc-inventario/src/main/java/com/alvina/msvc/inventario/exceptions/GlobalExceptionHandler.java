package com.alvina.msvc.inventario.exceptions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alvina.msvc.inventario.dtos.ErrorDTO;

public class GlobalExceptionHandler {

	private ErrorDTO createErrorDTO(int status, Date date, Map<String, String> errorMap) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus((status));
		errorDTO.setDate(date);
		errorDTO.setErrors(errorMap);

		return errorDTO;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> handleValidationFields(MethodArgumentNotValidException exception) {
		Map<String, String> errorMap = new HashMap<>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(this.createErrorDTO(HttpStatus.BAD_REQUEST.value(), new Date(), errorMap));
	}

	@ExceptionHandler(InventarioException.class)
	public ResponseEntity<ErrorDTO> handleProductoException(InventarioException exception) {
		Map<String, String> errorMap = Collections.singletonMap("Paciente Error", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(this.createErrorDTO(HttpStatus.NOT_FOUND.value(), new Date(), errorMap));

	}
}
