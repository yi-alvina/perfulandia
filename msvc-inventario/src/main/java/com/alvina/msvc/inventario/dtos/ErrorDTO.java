package com.alvina.msvc.inventario.dtos;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorDTO {
	private Integer status;
	private Date date;

	private Map<String, String> errors;

	@Override
	public String toString() {
		return "{" + "status=" + status + ", date=" + date + ", errors= " + errors + '}';
	}
}
